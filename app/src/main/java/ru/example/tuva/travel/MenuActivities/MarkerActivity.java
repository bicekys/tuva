package ru.example.tuva.travel.MenuActivities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ru.example.tuva.travel.R;

public class MarkerActivity extends AppCompatActivity implements /*GoogleMap.OnMarkerClickListener,*/
        OnMapReadyCallback {

    private static final LatLng HRAM = new LatLng(51.7096553, 94.44814725);
    private static final LatLng HEREXURY = new LatLng(50.599998, 95.001846);
    private static final LatLng USHBELDIR = new LatLng(51.4717472, 98.0534747);
    private static final LatLng CHASOVNYA = new LatLng(51.51403079, 92.33583927);
    private static final LatLng TOREHOL = new LatLng(50.0613266, 95.08083344);
    private static final LatLng AZAS = new LatLng(52.46478229, 96.11899853);
    private static final LatLng ALDYN_BULAK = new LatLng(51.5539883, 93.8866842);
    private static final LatLng SARYGSEP = new LatLng(51.50041476, 95.53741515);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

    }
    @Override
    public void onMapReady(GoogleMap map) {

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_dot_place);

        Marker hram = map.addMarker(new MarkerOptions()
                .position(HRAM)
                .icon(icon)
                .title("Свято-Троицкий храм Кызыла")
                .snippet("Кызыл, ул. Оюна Курседи, 112"));

        Marker herexury = map.addMarker(new MarkerOptions()
                .position(HEREXURY)
                .icon(icon)
                .title("Тувинские херексуры")
                .snippet("Республика Тыва, Тес-Хемский кожуун, дер. Самагалтай"));

        Marker ush = map.addMarker(new MarkerOptions()
                .position(USHBELDIR)
                .icon(icon)
                .title("Уш-Белдир")
                .snippet("Республика Тыва, Каа-Хемский кожуун, пос. Уш-Белдир"));

        Marker chasov = map.addMarker(new MarkerOptions()
                .position(CHASOVNYA)
                .icon(icon)
                .title("Часовня Саска")
                .snippet("Республика Тыва, Чаа-Хольский кожуун"));

        Marker tore = map.addMarker(new MarkerOptions()
                .position(TOREHOL)
                .icon(icon)
                .title("Озеро Торе-Холь")
                .snippet("Торе-Холь, Сарыг-Булунский сумон, Erzinsky Kozhuun, Tuva Republic"));

        Marker azas = map.addMarker(new MarkerOptions()
                .position(AZAS)
                .icon(icon)
                .title("Государственный природный заповедник Азас")
                .snippet("улица Агбаан, 20, село Тоора-Хем, Тоджинский кожуун, Республика Тыва"));

        Marker aldyn = map.addMarker(new MarkerOptions()
                .position(ALDYN_BULAK)
                .icon(icon)
                .title("Алдын_Булак")
                .snippet("Aldin-Bulak, «Енисей», Оттук-Даш, Усть-Элегестинский сумон, Ulug-Khemsky Kozhuun, Tuva Republic"));

        Marker saryg = map.addMarker(new MarkerOptions()
                .position(SARYGSEP)
                .icon(icon)
                .title("Сарыг-Сепский историко-мемориальный музей")
                .snippet("Республика Тыва, Каа-Хемский кожуун, дер. Сарыг-Сеп, ул. Енисейская, 258"));

    }
}








