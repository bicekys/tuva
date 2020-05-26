package ru.example.tuva.travel.MenuActivities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import ru.example.tuva.travel.Adapters.SightsViewAdapter;
import ru.example.tuva.travel.Model.Sights;
import ru.example.tuva.travel.R;

public class SightsActivity extends AppCompatActivity {
    private static final String TAG = "SightsActivity";

    //vars for Sights
    RecyclerView sightsRecyclerView;
    SightsViewAdapter sightsViewAdapter;
    List<Sights> sightsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);
        Log.d(TAG, "onCreate: started SightsActivity.");

        sightsRecyclerView = findViewById(R.id.sights_recyclerView);
        sightsRecyclerView.setHasFixedSize(true);
        sightsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        sightsList = new ArrayList<>();
        getIncomingIntent();
        sightsViewAdapter = new SightsViewAdapter(this, sightsList);
        sightsRecyclerView.setAdapter(sightsViewAdapter);
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String cityTitle = bundle.getString("sights_city_title");

            assert cityTitle != null;

            if(cityTitle.equals("Torehol")){ Torehol(); }

            if(cityTitle.equals("in Bukhara")){ Bukhara(); }

            if(cityTitle.equals("Azas")){ Azas(); }

            //if(cityTitle.equals("in Khiva")){ Khiva(); }

            //if(cityTitle.equals("in Nukus")){ Nukus(); }

            //if(cityTitle.equals("in Kokand")){ Kokand(); }

         //   if(cityTitle.equals("in Fergana")){ Fergana(); }
            //Termiz
          //  if(cityTitle.equals("in Termiz")){ Termiz(); }
            //Andijon
           // if(cityTitle.equals("in Andijon")){ Andijon(); }
            //Shakhrisabz
           // if(cityTitle.equals("in Shakhrisabz")){ Shakhrisabz(); }

            setImage(cityTitle);
        }
    }

    private void setImage(String cityTitle){
        Log.d(TAG, "setImage: setting image and name to widgets.");
        TextView cTitle = findViewById(R.id.sights_desc);
        cTitle.setText(cityTitle);
    }

    private void Torehol() {
        List<Sights> sights = sightsList;
        sights.add(
                new Sights(1,
                        "History Museum",
                        "museum in Tuva",
                        R.drawable.sights_tashkent_history_museum,
                        "Hours : 9.30AM - 6PM (Tue - Sun)\nPrice : admission 10,000 UZS\n            camera 25,000 UZS\n            English guided tour 8,000 UZS\nAddress : 3 Buyuk Turon St, Tashkent\nTel : (+998)71 239 17 79",
                        "The History Museum is a must-visit for anyone looking for a primer on the history of Turkestan from its earliest settlements 5000 years ago to the present. The 2nd floor has some fine Zoroastrian and Buddhist artefacts, including several 1st- to 3rd-century Buddhas and Buddha fragments from Fayoz-Tepe area near Termiz.\n" +
                                "\nOn the 3rd floor English placards walk you through the Russian conquests of the khanates and emirates, and there are some foreboding newspaper clippings of revolts in Andijon being brutally suppressed by the Russians in 1915. On the 4th floor you’ll also find the normal ode to post-independence gas plants and first President Karimov."));
        sights.add(
                new Sights(1,
                        "The state museum of arts of Uzbekistan",
                        "museum in Tashkent",
                        R.drawable.sights_tashkent_the_state_museum_of_arts,
                        "Hours : 10AM - 5PM\nPrice : admission 10,000 UZS\n            camera 50,000 UZS\n            guide 40,000 UZS\nAddress : 16 Amir Timur Av, Tashkent\nTel : (+998)71 236 74 36\nWebsite : www.stateartmuseum.uz/en",
                        "The four floors of this excellent museum walk you through 1500 years of art in Uzbekistan, from 7th-century Buddhist relics from Kuva and the Greek-inspired head of Hercules from Khalchayan near Termiz, to the art of Soviet Uzbekistan. There are even a few 19th-century paintings of second-tier Russian and European artists hanging about. There’s an impressive section on Uzbek applied art – notably some lovely ghanch (plaster carvings) from Bukhara, carved wooden doors from Khiva and the silk-on-cotton embroidered hangings called suzani."));

    }

    private void Bukhara() {
    }

    private void Azas() {
        List<Sights> sights = sightsList;
        sights.add(
                new Sights(1,
                        "Registan",
                        "plaza in Samarkand",
                        R.drawable.sights_samarkand_registan,
                        "Hours : 8AM - 7PM (Apr - Oct)\n             9AM - 5PM (Nov - Mar)\nPrice : admission 30,000 UZS\nAddress : Registan St, Samarkand\nTel : (+998)66 235 38 26\nWebsite : registon.uz/en",
                        "This ensemble of majestic, tilting medressas – a near-overload of majolica, azure mosaics and vast, well-proportioned spaces – is the centrepiece of the city, and arguably the most awesome single sight in Central Asia. The three grand edifices here are among the world’s oldest preserved medressas, anything older having been destroyed by Chinggis Khan.\n" +
                                "\nThe Registan, which translates to ‘Sandy Place’ in Tajik, was medieval Samarkand’s commercial centre and the plaza was probably a wall-to-wall bazaar. The three medressas have taken their knocks over the years courtesy of the frequent earthquakes that buffet the region; that they are still standing is a testament to the incredible craftsmanship of their builders. The Soviets, to their credit, worked feverishly to restore these beleaguered treasures, but they also took some questionable liberties, such as the capricious addition of a blue outer dome to the Tilla-Kari Medressa. For an idea of just how ruined the medressas were at the start of the 20th century, check out the excellent photo exhibit inside the Tilla-Kari Medressa.\n" +
                                "\nThe Ulugbek Medressa, on the western side, is the original medressa, finished in 1420 under Ulugbek who is said to have taught mathematics here (other subjects taught here included theology, astronomy and philosophy). The stars on the portal reflect Ulugbek's love of astronomy. Beneath the little corner domes were lecture halls, now housing displays on Ulugbek, including copies of the 'Zij' (his writings on astronomy) and miniatures depicting Central Asian astronomers at work. At the rear is a large mosque with a beautiful blue painted interior and an austere teaching room to one side. Police guards occasionally offer to clandestinely escort visitors to the top of the medressa's minaret for around US$10.\n" +
                                "\nThe other buildings are rough imitations by the Shaybanid Emir Yalangtush. The entrance portal of the Sher Dor (Lion) Medressa, opposite Ulugbek’s and finished in 1636, is decorated with roaring felines that look like tigers but are meant to be lions. The lions, the deer they are chasing and the Mongolian-faced, Zorostrian-inspired suns rising from their backs are all unusual, flouting Islamic prohibitions against the depiction of live animals. It took 17 years to build but hasn’t held up as well as the Ulugbek Medressa, built in just three years.\n" +
                                "\nIn between them is the Tilla-Kari (Gold-Covered) Medressa, completed in 1660, with a pleasant, gardenlike courtyard. The highlight here is the mosque, which is on the left-hand side of the courtyard and is intricately decorated with blue and gold to symbolise Samarkand’s wealth. The mosque’s delicate ceiling, oozing gold leaf, is flat but its tapered design makes it look domed from the inside. The result is magnificent. Inside the mosque is an interesting picture gallery featuring blown-up B&W photos of old Samarkand. Several shops sell prints of these old photos.\n" +
                                "\nMost of the medressas’ former dormitory rooms are now art and souvenir shops. Be sure to visit the Registan in the evening to see if the impressive sound and light show is being projected. If a large group has paid for the show then other visitors can watch for free.\n" +
                                "\nNote that your entrance ticket is valid all day long, allowing you to come back and photograph the complex at the various times of day needed for the sunlight to be coming from the right direction. However, tell the complex security guards if you'd like to do this, otherwise they will tear your ticket and you won't be able to reuse it."));
        sights.add(
                new Sights(1,
                        "Gur-e-Amir Mausoleum",
                        "mausoleum in Samarkand",
                        R.drawable.sights_samarkand_gur_e_amir,
                        "Hours : 9AM - 7PM (Apr - Oct)\n             9AM - 5PM (Nov - Mar)\nPrice : admission 22,000 UZS\n            camera 5,000 UZS\nAddress : Bo'stonsaroy St, Samarkand",
                        "The beautiful portal and trademark fluted azure dome of the Gur-e-Amir Mausoleum marks the final resting place of Timur (Tamerlane), along with two sons and two grandsons (including Ulugbek). It's a surprisingly modest building, largely because Timur was never expecting to be buried here. The tilework and dome are particularly beautiful; be sure to return at night when the building is spotlit to grand effect.\n" +
                                "\nTimur had built a simple crypt for himself at Shakhrisabz, and had this one built in 1404 for his grandson and proposed heir, Mohammed Sultan, who had died the previous year. But the story goes that when Timur died unexpectedly of pneumonia in Kazakhstan (in the course of planning an expedition against the Chinese) in the winter of 1405, the passes back to Shakhrisabz were snowed in and he was interred here instead.\n" +
                                "\nAs with other Muslim mausoleums, the stones are just markers; the actual crypts are in a chamber beneath. In the centre is Timur’s stone, once a single block of dark-green jade. In 1740 the warlord Nadir Shah carried it off to Persia, where it was accidentally broken in two – from which time Nadir Shah is said to have had a run of very bad luck, including the near death of his son. At the urging of his religious advisers he returned the stone to Samarkand and, of course, his son recovered.\n" +
                                "\nThe plain marble marker to the left of Timur’s is that of Ulugbek; to the right is that of Mir Said Baraka, one of Timur’s spiritual advisors. In front lies Mohammed Sultan. The stones behind Timur’s mark the graves of his sons Shah Rukh (the father of Ulugbek) and Miran Shah. Behind these lies Sheikh Seyid Umar, the most revered of Timur’s teachers, said to be a descendant of the Prophet Mohammed. Timur ordered Gur-e-Amir built around Umar’s tomb.\n" +
                                "\nThe Soviet anthropologist Mikhail Gerasimov opened the crypts in 1941 and, among other things, confirmed that Timur was tall (1.7m) and lame in the right leg and right arm (from injuries suffered when he was 25) – and that Ulugbek died from being beheaded. According to every tour guide’s favourite anecdote, he found on Timur’s grave an inscription to the effect that ‘whoever opens this will be defeated by an enemy more fearsome than I’. The next day, 22 June, Hitler attacked the Soviet Union."));


}
