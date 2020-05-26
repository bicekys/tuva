package ru.example.tuva.travel.MenuActivities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import ru.example.tuva.travel.Adapters.CustomInfoWindowAdapter;
import ru.example.tuva.travel.Adapters.PlaceAutoCompleteAdapter;
import ru.example.tuva.travel.R;
import ru.example.tuva.travel.SightsInnerActivity;
import ru.example.tuva.travel.modules.TaskLoadedCallback;
import ru.example.tuva.travel.modules.PlaceInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, TaskLoadedCallback {

    private static final LatLng Uzb = new LatLng(51.7190995, 94.4300627);
      //Origin and Destionation MarkerOptions vars for direction
    private static MarkerOptions Destination = null;
    private static MarkerOptions Origin = null;


    private final static String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;
    private static final float DEFAULT_ZOOM = 15f;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(55.9289172707, 37.1449940049),
            new LatLng(73.055417108, 45.5868043076));


    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps, mInfo;
    private ImageView directionButton;
    private Polyline currentPolyline;

    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private PlaceAutoCompleteAdapter mPlaceAutoCompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private PlaceInfo mPlace;
    private Marker mMarker;
    private String cities;
    Location currentLocation;


    //initialize MarkerActivity class
    MarkerActivity mMarkerAction = new MarkerActivity();

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;
        moveCamera(Uzb, 7f, "TUVA");

        if(cities!= null)
            cameraZoom();

        mMap.clear();

        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false); // to disable my location button
            init();

            //call MarkerActivity class methods
            mMarkerAction.onMapReady(mMap);
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    String sights_title = marker.getTitle();

                    Intent intent = new Intent(MainMapActivity.this, SightsInnerActivity.class);

                    if (sights_title.equals("Свято-Троицкий храм Кызыла")){
                        int sights_bg = R.drawable.sights_samarkand_registan;
                        String sights_desc = "plaza in Samarkand";
                        String sights_details = "Hours : 8AM - 7PM (Apr - Oct)\n             9AM - 5PM (Nov - Mar)\nPrice : admission 30,000 UZS\nAddress : Registan St, Samarkand\nTel : (+998)66 235 38 26\nWebsite : registon.uz/en";
                        String sights_full_desc = "This ensemble of majestic, tilting medressas – a near-overload of majolica, azure mosaics and vast, well-proportioned spaces – is the centrepiece of the city, and arguably the most awesome single sight in Central Asia. The three grand edifices here are among the world’s oldest preserved medressas, anything older having been destroyed by Chinggis Khan.\n" +
                                        "\nThe Registan, which translates to ‘Sandy Place’ in Tajik, was medieval Samarkand’s commercial centre and the plaza was probably a wall-to-wall bazaar. The three medressas have taken their knocks over the years courtesy of the frequent earthquakes that buffet the region; that they are still standing is a testament to the incredible craftsmanship of their builders. The Soviets, to their credit, worked feverishly to restore these beleaguered treasures, but they also took some questionable liberties, such as the capricious addition of a blue outer dome to the Tilla-Kari Medressa. For an idea of just how ruined the medressas were at the start of the 20th century, check out the excellent photo exhibit inside the Tilla-Kari Medressa.\n" +
                                        "\nThe Ulugbek Medressa, on the western side, is the original medressa, finished in 1420 under Ulugbek who is said to have taught mathematics here (other subjects taught here included theology, astronomy and philosophy). The stars on the portal reflect Ulugbek's love of astronomy. Beneath the little corner domes were lecture halls, now housing displays on Ulugbek, including copies of the 'Zij' (his writings on astronomy) and miniatures depicting Central Asian astronomers at work. At the rear is a large mosque with a beautiful blue painted interior and an austere teaching room to one side. Police guards occasionally offer to clandestinely escort visitors to the top of the medressa's minaret for around US$10.\n" +
                                        "\nThe other buildings are rough imitations by the Shaybanid Emir Yalangtush. The entrance portal of the Sher Dor (Lion) Medressa, opposite Ulugbek’s and finished in 1636, is decorated with roaring felines that look like tigers but are meant to be lions. The lions, the deer they are chasing and the Mongolian-faced, Zorostrian-inspired suns rising from their backs are all unusual, flouting Islamic prohibitions against the depiction of live animals. It took 17 years to build but hasn’t held up as well as the Ulugbek Medressa, built in just three years.\n" +
                                        "\nIn between them is the Tilla-Kari (Gold-Covered) Medressa, completed in 1660, with a pleasant, gardenlike courtyard. The highlight here is the mosque, which is on the left-hand side of the courtyard and is intricately decorated with blue and gold to symbolise Samarkand’s wealth. The mosque’s delicate ceiling, oozing gold leaf, is flat but its tapered design makes it look domed from the inside. The result is magnificent. Inside the mosque is an interesting picture gallery featuring blown-up B&W photos of old Samarkand. Several shops sell prints of these old photos.\n" +
                                        "\nMost of the medressas’ former dormitory rooms are now art and souvenir shops. Be sure to visit the Registan in the evening to see if the impressive sound and light show is being projected. If a large group has paid for the show then other visitors can watch for free.\n" +
                                        "\nNote that your entrance ticket is valid all day long, allowing you to come back and photograph the complex at the various times of day needed for the sunlight to be coming from the right direction. However, tell the complex security guards if you'd like to do this, otherwise they will tear your ticket and you won't be able to reuse it.";
                        intent.putExtra("sights_bg", sights_bg);
                        intent.putExtra("sights_title", sights_title);
                        intent.putExtra("sights_desc", sights_desc);
                        intent.putExtra("sights_details", sights_details);
                        intent.putExtra("sights_full_desc", sights_full_desc);
                    }

                    if (sights_title.equals("Тувинские херексуры")){
                        int sights_bg =  R.drawable.sights_samarkand_gur_e_amir;
                        String sights_desc = "mausoleum in Samarkand";
                        String sights_details =  "Hours : 9AM - 7PM (Apr - Oct)\n             9AM - 5PM (Nov - Mar)\nPrice : admission 22,000 UZS\n            camera 5,000 UZS\nAddress : Bo'stonsaroy St, Samarkand";
                        String sights_full_desc = "The beautiful portal and trademark fluted azure dome of the Gur-e-Amir Mausoleum marks the final resting place of Timur (Tamerlane), along with two sons and two grandsons (including Ulugbek). It's a surprisingly modest building, largely because Timur was never expecting to be buried here. The tilework and dome are particularly beautiful; be sure to return at night when the building is spotlit to grand effect.\n" +
                                "\nTimur had built a simple crypt for himself at Shakhrisabz, and had this one built in 1404 for his grandson and proposed heir, Mohammed Sultan, who had died the previous year. But the story goes that when Timur died unexpectedly of pneumonia in Kazakhstan (in the course of planning an expedition against the Chinese) in the winter of 1405, the passes back to Shakhrisabz were snowed in and he was interred here instead.\n" +
                                "\nAs with other Muslim mausoleums, the stones are just markers; the actual crypts are in a chamber beneath. In the centre is Timur’s stone, once a single block of dark-green jade. In 1740 the warlord Nadir Shah carried it off to Persia, where it was accidentally broken in two – from which time Nadir Shah is said to have had a run of very bad luck, including the near death of his son. At the urging of his religious advisers he returned the stone to Samarkand and, of course, his son recovered.\n" +
                                "\nThe plain marble marker to the left of Timur’s is that of Ulugbek; to the right is that of Mir Said Baraka, one of Timur’s spiritual advisors. In front lies Mohammed Sultan. The stones behind Timur’s mark the graves of his sons Shah Rukh (the father of Ulugbek) and Miran Shah. Behind these lies Sheikh Seyid Umar, the most revered of Timur’s teachers, said to be a descendant of the Prophet Mohammed. Timur ordered Gur-e-Amir built around Umar’s tomb.\n" +
                                "\nThe Soviet anthropologist Mikhail Gerasimov opened the crypts in 1941 and, among other things, confirmed that Timur was tall (1.7m) and lame in the right leg and right arm (from injuries suffered when he was 25) – and that Ulugbek died from being beheaded. According to every tour guide’s favourite anecdote, he found on Timur’s grave an inscription to the effect that ‘whoever opens this will be defeated by an enemy more fearsome than I’. The next day, 22 June, Hitler attacked the Soviet Union.";
                        intent.putExtra("sights_bg", sights_bg);
                        intent.putExtra("sights_title", sights_title);
                        intent.putExtra("sights_desc", sights_desc);
                        intent.putExtra("sights_details", sights_details);
                        intent.putExtra("sights_full_desc", sights_full_desc);
                    }

                    if (sights_title.equals("Уш-Белдир")){
                        int sights_bg =  R.drawable.sights_samarkand_shah_i_zinda;
                        String sights_desc = "islamic tomb in Samarkand";
                        String sights_details = "Hours : 7AM - 7PM\nPrice : admission 10,000 UZS\n            camera 7,000 UZS\nAddress : Shah-i-Zinda St, Samarkand\nTel : (+998)71 233 53 82";
                        String sights_full_desc = "Samarkand’s most moving and beloved site is this stunning avenue of mausoleums, which contains some of the richest tilework in the Muslim world. The name, which means ‘Tomb of the Living King’, refers to its original, innermost and holiest shrine – a complex of cool, quiet rooms around what is probably the grave of Qusam ibn-Abbas, who is said to have brought Islam to this area in the 7th century. The most stunning Timurid-era tilework dates from 14th and 15th centuries.\n" +
                                "\nA shrine to Qusam, a cousin of the Prophet Mohammed, existed here on the edge of Afrosiab for around seven centuries before Timur (Tamerlane) and later Ulugbek buried their family and favourites near the sanctity of the original shrine.\n" +
                                "\nThe most beautiful tomb is the Shodi Mulk Oko Mausoleum (1372), resting place of a sister and niece of Timur, second on the left after the entry stairs. The exquisite majolica and terracotta work here – notice the minuscule amount of space between the tiles – was of such exceptional quality that it merited almost no restoration.\n" +
                                "\nAfter remarkably surviving more than seven centuries with only minor touch-up work, many of the tombs were aggressively and controversially restored in 2005. As a result, much of the brilliant mosaic, majolica and terracotta work you see today is not original.\n" +
                                "\nShah-i-Zinda is an important place of pilgrimage, so enter with respect and dress conservatively. Just outside the entrance are the foundations of a 15th-century tahorathana (bathhouse). At the end of the pathway between the mausoleums, the complex opens up into Samarkand's main cemetery, which is a fascinating place to walk.";
                        intent.putExtra("sights_bg", sights_bg);
                        intent.putExtra("sights_title", sights_title);
                        intent.putExtra("sights_desc", sights_desc);
                        intent.putExtra("sights_details", sights_details);
                        intent.putExtra("sights_full_desc", sights_full_desc);
                    }

                    if (sights_title.equals("Часовня Саска")){
                        int sights_bg = R.drawable.sights_samarkand_bibi_khanym;
                        String sights_desc = "mosque in Samarkand";
                        String sights_details = "Hours : 8AM - 8PM\nPrice : admission 22,000 UZS\n            camera 5,000 UZS\nAddress : Bibikhonim St, Samarqand\nTel : (+998)97 793 46 75";
                        String sights_full_desc = "The enormous congregational Bibi-Khanym Mosque, northeast of the Registan, was financed from the spoils of Timur's invasion of India and must have been the jewel of his empire. Once one of the Islamic world’s biggest mosques (the cupola of the main mosque is 41m high and the pishtak or entrace portal, 38m), it pushed contemporary construction techniques to the limit, so much so that the dome started crumbling even before construction had finished.\n" +
                                "\nThe mosque partially collapsed in an earthquake in 1897 before being rebuilt in the 1970s and more rapidly in the years after independence.\n" +
                                "\nLegend says that Bibi-Khanym, Timur’s Chinese wife, ordered the mosque built as a surprise while he was away. The architect fell madly in love with her and refused to finish the job unless he could give her a kiss. The smooch left a mark and Timur, on seeing it, executed the architect and decreed that women should henceforth wear veils so as not to tempt other men.\n" +
                                "\nThe interior courtyard contains an enormous marble Quran stand that lends some scale to the place. Local lore has it that any woman who crawls under the stand will have lots of children. The courtyard also contains two smaller mosques. The one on the left as you enter through the enormous main gate has an impressive unrestored interior festooned with Arabic calligraphy.";

                        intent.putExtra("sights_bg", sights_bg);
                        intent.putExtra("sights_title", sights_title);
                        intent.putExtra("sights_desc", sights_desc);
                        intent.putExtra("sights_details", sights_details);
                        intent.putExtra("sights_full_desc", sights_full_desc);
                    }

                    if (sights_title.equals("Озеро Торе-Холь")){
                        int sights_bg = R.drawable.torehol;
                        String sights_desc = "mosque in Samarkand";
                        String sights_details = "Hours : 8AM - 8PM\nPrice : admission 22,000 UZS\n            camera 5,000 UZS\nAddress : Bibikhonim St, Samarqand\nTel : (+998)97 793 46 75";
                        String sights_full_desc = "The enormous congregational Bibi-Khanym Mosque, northeast of the Registan, was financed from the spoils of Timur's invasion of India and must have been the jewel of his empire. Once one of the Islamic world’s biggest mosques (the cupola of the main mosque is 41m high and the pishtak or entrace portal, 38m), it pushed contemporary construction techniques to the limit, so much so that the dome started crumbling even before construction had finished.\n" +
                                "\nThe mosque partially collapsed in an earthquake in 1897 before being rebuilt in the 1970s and more rapidly in the years after independence.\n" +
                                "\nLegend says that Bibi-Khanym, Timur’s Chinese wife, ordered the mosque built as a surprise while he was away. The architect fell madly in love with her and refused to finish the job unless he could give her a kiss. The smooch left a mark and Timur, on seeing it, executed the architect and decreed that women should henceforth wear veils so as not to tempt other men.\n" +
                                "\nThe interior courtyard contains an enormous marble Quran stand that lends some scale to the place. Local lore has it that any woman who crawls under the stand will have lots of children. The courtyard also contains two smaller mosques. The one on the left as you enter through the enormous main gate has an impressive unrestored interior festooned with Arabic calligraphy.";

                        intent.putExtra("sights_bg", sights_bg);
                        intent.putExtra("sights_title", sights_title);
                        intent.putExtra("sights_desc", sights_desc);
                        intent.putExtra("sights_details", sights_details);
                        intent.putExtra("sights_full_desc", sights_full_desc);
                    }


                    if (sights_title.equals("Государственный природный заповедник Азас")){
                        int sights_bg = R.drawable.azas;
                        String sights_desc = "mosque in Samarkand";
                        String sights_details = "Hours : 8AM - 8PM\nPrice : admission 22,000 UZS\n            camera 5,000 UZS\nAddress : Bibikhonim St, Samarqand\nTel : (+998)97 793 46 75";
                        String sights_full_desc = "The enormous congregational Bibi-Khanym Mosque, northeast of the Registan, was financed from the spoils of Timur's invasion of India and must have been the jewel of his empire. Once one of the Islamic world’s biggest mosques (the cupola of the main mosque is 41m high and the pishtak or entrace portal, 38m), it pushed contemporary construction techniques to the limit, so much so that the dome started crumbling even before construction had finished.\n" +
                                "\nThe mosque partially collapsed in an earthquake in 1897 before being rebuilt in the 1970s and more rapidly in the years after independence.\n" +
                                "\nLegend says that Bibi-Khanym, Timur’s Chinese wife, ordered the mosque built as a surprise while he was away. The architect fell madly in love with her and refused to finish the job unless he could give her a kiss. The smooch left a mark and Timur, on seeing it, executed the architect and decreed that women should henceforth wear veils so as not to tempt other men.\n" +
                                "\nThe interior courtyard contains an enormous marble Quran stand that lends some scale to the place. Local lore has it that any woman who crawls under the stand will have lots of children. The courtyard also contains two smaller mosques. The one on the left as you enter through the enormous main gate has an impressive unrestored interior festooned with Arabic calligraphy.";

                        intent.putExtra("sights_bg", sights_bg);
                        intent.putExtra("sights_title", sights_title);
                        intent.putExtra("sights_desc", sights_desc);
                        intent.putExtra("sights_details", sights_details);
                        intent.putExtra("sights_full_desc", sights_full_desc);
                    }


                    if (sights_title.equals("Алдын_Булак")){
                        int sights_bg = R.drawable.aldyn;
                        String sights_desc = "mosque in Samarkand";
                        String sights_details = "Hours : 8AM - 8PM\nPrice : admission 22,000 UZS\n            camera 5,000 UZS\nAddress : Bibikhonim St, Samarqand\nTel : (+998)97 793 46 75";
                        String sights_full_desc = "The enormous congregational Bibi-Khanym Mosque, northeast of the Registan, was financed from the spoils of Timur's invasion of India and must have been the jewel of his empire. Once one of the Islamic world’s biggest mosques (the cupola of the main mosque is 41m high and the pishtak or entrace portal, 38m), it pushed contemporary construction techniques to the limit, so much so that the dome started crumbling even before construction had finished.\n" +
                                "\nThe mosque partially collapsed in an earthquake in 1897 before being rebuilt in the 1970s and more rapidly in the years after independence.\n" +
                                "\nLegend says that Bibi-Khanym, Timur’s Chinese wife, ordered the mosque built as a surprise while he was away. The architect fell madly in love with her and refused to finish the job unless he could give her a kiss. The smooch left a mark and Timur, on seeing it, executed the architect and decreed that women should henceforth wear veils so as not to tempt other men.\n" +
                                "\nThe interior courtyard contains an enormous marble Quran stand that lends some scale to the place. Local lore has it that any woman who crawls under the stand will have lots of children. The courtyard also contains two smaller mosques. The one on the left as you enter through the enormous main gate has an impressive unrestored interior festooned with Arabic calligraphy.";

                        intent.putExtra("sights_bg", sights_bg);
                        intent.putExtra("sights_title", sights_title);
                        intent.putExtra("sights_desc", sights_desc);
                        intent.putExtra("sights_details", sights_details);
                        intent.putExtra("sights_full_desc", sights_full_desc);
                    }

                    if (sights_title.equals("Сарыг-Сепский историко-мемориальный музей")){
                        int sights_bg = R.drawable.aldyn;
                        String sights_desc = "mosque in Samarkand";
                        String sights_details = "Hours : 8AM - 8PM\nPrice : admission 22,000 UZS\n            camera 5,000 UZS\nAddress : Bibikhonim St, Samarqand\nTel : (+998)97 793 46 75";
                        String sights_full_desc = "The enormous congregational Bibi-Khanym Mosque, northeast of the Registan, was financed from the spoils of Timur's invasion of India and must have been the jewel of his empire. Once one of the Islamic world’s biggest mosques (the cupola of the main mosque is 41m high and the pishtak or entrace portal, 38m), it pushed contemporary construction techniques to the limit, so much so that the dome started crumbling even before construction had finished.\n" +
                                "\nThe mosque partially collapsed in an earthquake in 1897 before being rebuilt in the 1970s and more rapidly in the years after independence.\n" +
                                "\nLegend says that Bibi-Khanym, Timur’s Chinese wife, ordered the mosque built as a surprise while he was away. The architect fell madly in love with her and refused to finish the job unless he could give her a kiss. The smooch left a mark and Timur, on seeing it, executed the architect and decreed that women should henceforth wear veils so as not to tempt other men.\n" +
                                "\nThe interior courtyard contains an enormous marble Quran stand that lends some scale to the place. Local lore has it that any woman who crawls under the stand will have lots of children. The courtyard also contains two smaller mosques. The one on the left as you enter through the enormous main gate has an impressive unrestored interior festooned with Arabic calligraphy.";

                        intent.putExtra("sights_bg", sights_bg);
                        intent.putExtra("sights_title", sights_title);
                        intent.putExtra("sights_desc", sights_desc);
                        intent.putExtra("sights_details", sights_details);
                        intent.putExtra("sights_full_desc", sights_full_desc);
                    }

                    startActivity(intent);
                }
            });

            mMap.setOnMarkerClickListener(new MainMapActivity() {
                public boolean onMarkerClick(Marker marker){
                    Destination = new MarkerOptions().position(new LatLng(marker.getPosition().latitude,marker.getPosition().longitude));
                    Toast.makeText(MainMapActivity.this, "Marker Clicked at " + marker.getTitle(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }


    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mSearchText =  findViewById(R.id.input_search);
        mGps = findViewById(R.id.ic_gps);
        mInfo = findViewById(R.id.place_info);
        directionButton =  findViewById(R.id.get_direction);
        getLocationPermission();

        getIncomingIntent();
    }

    private void init() {
        Log.d(TAG, "init: initializing");

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        mSearchText.setOnItemClickListener(mAutocompleteClickListener);

        mPlaceAutoCompleteAdapter = new PlaceAutoCompleteAdapter(this, mGoogleApiClient,
                LAT_LNG_BOUNDS, null);
        mSearchText.setAdapter(mPlaceAutoCompleteAdapter);

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
                    //execute our method for searching
                    geoLocate();
                }
                return false;

            }
        });
        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked gps icon");
                moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM,
                        "My location");
            }
        });
        hideSoftKeyboard();
        //Info window Button
        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked place info");
                try {
                    if (mMarker.isInfoWindowShown()) {
                        mMarker.hideInfoWindow();
                    } else {
                        Log.d(TAG, "onClick: place info: " + mPlace.toString());
                        mMarker.showInfoWindow();
                    }

                } catch (NullPointerException e) {
                    Log.e(TAG, "onClick: NullPointerException" + e.getMessage());
                }
            }
        });

        directionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Destination != null) {
                    Log.d(TAG, "onClick: Got direction");
                    Toast.makeText(MainMapActivity.this, "Direction received", Toast.LENGTH_SHORT).show();
                //    new FetchURL(MainMapActivity.this).execute(getUrl(Tash_Airport.getPosition(), Destination.getPosition(), "driving"), "driving");
                }
                else
                    Toast.makeText(MainMapActivity.this, "Please select the destination marker", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void geoLocate() {
        Log.d(TAG, "geoLocate: geolocationg");
        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(MainMapActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }

        if (list.size() > 0) {
            Address address = list.get(0);
            Log.d(TAG, "geoLocate: found a location" + address.toString());
            // Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                    address.getAddressLine(0));
        }
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices location");
        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionsGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();

                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Log.d(TAG, "onComplete: found location!");
                            currentLocation = (Location) task.getResult();
                            //origin of directions, as we use current location as origin we initialize it here if location is successful
                            Origin = new MarkerOptions().position(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()));
                        } else {
                            Toast.makeText(MainMapActivity.this, "Turn on your location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: Security Exception" + e.getMessage());
        }
    }

    // Info Window
    private void moveCamera(LatLng latLng, float zoom, PlaceInfo placeInfo) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng:" + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        mMap.clear();

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MainMapActivity.this));

        if (placeInfo != null) {
            try {
                String snippet = "Address: " + placeInfo.getAddress() + "\n" /*+
                        "Phone number: " + placeInfo.getPhoneNumber() + "\n" +
                        "Website: " + placeInfo.getWebsiteUri() + "\n" +
                        "Price rating: " + placeInfo.getRating() + "\n"*/;
                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title(placeInfo.getName())
                        .snippet(snippet);

                mMarker = mMap.addMarker(options);

            } catch (NullPointerException e) {
                Log.e(TAG, "moveCamera: " + e.getMessage());
            }
        } else {
            mMap.addMarker(new MarkerOptions().position(latLng));
        }

        mMarkerAction.onMapReady(mMap);
        hideSoftKeyboard();
    }

    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng:" + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        if (!title.equals("My location")) {
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            //mMap.addMarker(options);
        }
        hideSoftKeyboard();
    }

    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainMapActivity.this);

    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }

    //method used to hide the title and keyboard after searching the location
    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSearchText.getWindowToken(), 0);
    }

//camera zoom on map icon click in specific cities
    public void cameraZoom(){

        switch (cities){
            case "Samarkand":
                moveCamera(new LatLng(39.658678, 66.977348), 13f, cities);
                break;
        }
    }
    /*
    ----------------------- google places API autocomplete suggestion
     */

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
            hideSoftKeyboard();
            final AutocompletePrediction item = mPlaceAutoCompleteAdapter.getItem(i);
            final String placeID = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeID);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
               Log.d(TAG, "onResult: Place query did not complete successfully" + places.getStatus().toString());
                places.release();
                return;
            }
            final Place place = places.get(0);
            try {
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                Log.d(TAG, "onResult: Name: " + place.getName());
                mPlace.setAddress(place.getAddress().toString());
                Log.d(TAG, "onResult: Address: " + place.getAddress());
                mPlace.setAttributions(place.getAttributions().toString());
                Log.d(TAG, "onResult: Attributions: " + place.getAttributions());
                mPlace.setId(place.getId());
                Log.d(TAG, "onResult: ID: " + place.getId());
                mPlace.setLatlng(place.getLatLng());
                Log.d(TAG, "onResult: LatLng of search: " + place.getLatLng());
                // mPlace.setRating(place.getRating());
                Log.d(TAG, "onResult: Rating: " + place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                // Log.d(TAG, "onResult: Phone Number: " + place.getPhoneNumber() );
                // mPlace.setWebsiteUri(place.getWebsiteUri());
                Log.d(TAG, "onResult: Website URL: " + place.getWebsiteUri());

                Log.d(TAG, "onResult: place: " + place.getLatLng());
            } catch (NullPointerException e) {
                Log.e(TAG, "onResult: NullPointerException: " + e.getMessage());
            }

            moveCamera(new LatLng(place.getViewport().getCenter().latitude,
                    place.getViewport().getCenter().longitude), 15f, mPlace);
            places.release();
        }
    };


    @Override
    public void onInfoWindowClick(Marker marker) {
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }



    /*-------------------------------------- Used for Map Directions ------------------------------*/

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }


    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            cities = bundle.getString("map_city_title");
        }
    }
}


