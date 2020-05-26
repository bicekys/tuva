package ru.example.tuva.travel.MenuActivities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import ru.example.tuva.travel.Adapters.RestaurantsViewAdapter;
import ru.example.tuva.travel.Model.Restaurants;
import ru.example.tuva.travel.R;

public class RestaurantsActivity extends AppCompatActivity {
    private static final String TAG = "RestaurantsActivity";
    //vars for Sights
    RecyclerView restaurantsRecyclerView;
    RestaurantsViewAdapter restaurantsViewAdapter;
    List<Restaurants> restaurantsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        restaurantsRecyclerView = findViewById(R.id.restaurants_recyclerView);
        restaurantsRecyclerView.setHasFixedSize(true);
        restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        restaurantsList = new ArrayList<>();

        getIncomingIntent();

        restaurantsViewAdapter = new RestaurantsViewAdapter(this, restaurantsList);
        restaurantsRecyclerView.setAdapter(restaurantsViewAdapter);
    }
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String restaurantsCityTitle = bundle.getString("restaurants_city_title");

            assert restaurantsCityTitle != null;
            //Tashkent
            if(restaurantsCityTitle.equals("Torehol")){ Torehol(); }

                       if(restaurantsCityTitle.equals("Azas")){ Azas(); }
                       setImage(restaurantsCityTitle);
        }
    }

    private void setImage(String restaurantsCityTitle){
        Log.d(TAG, "setImage: setting image and name to widgets.");
        TextView cTitle = findViewById(R.id.restaurants_city);
        cTitle.setText(restaurantsCityTitle);
    }

    private void Torehol() {
        List<Restaurants> restaurants = restaurantsList;
        restaurants.add(
                new Restaurants(1,
                        "База отдыха Торе Хол",
                        "озеро",
                        R.drawable.tore,
                        "Hours : noon - 11PM\nPrice  : mains 22,000 - 47,000 \nAddress : ozero Azas, Todzha\nTel : (+913)71 252 56 83",
                        "находится в Тоджинском районе республики Тыва. Расстояние от Кызыла до районного центра – села Тоора-Хем наземным путём составляет 230 километров, водным – 285 километров, воздушным – 170 километров. 30% водной глади озера расположено в заповеднике с одноименным названием. "));
        restaurants.add(
                new Restaurants(1,
                        "Toshtug",
                        "озеро",
                        R.drawable.tore,
                        "Hours : noon - 11PM (Mon - Sat)\n              5PM - 11PM (Sun)\nPrice : mains 25,000 - 60,000\nAddress :  Todzha\nTel : (+913)71 255 42 00",
                        "крупнейшее проточное озеро Тоджинской котловины, которое является памятником природы. Оно расположено на высоте 944 метра, длина составляет 20 километров, средняя ширина – 5 - 7 километров. Оно протянулось с запада на восток."));
          }

       private void Azas() {
           List<Restaurants> restaurants = restaurantsList;
           restaurants.add(
                   new Restaurants(1,
                           "Platan",
                           "International",
                           R.drawable.tore,
                           "Hours : 10AM - 11PM\nPrice  : mains 20,000 - 28,000 \nTel : (+913)66 233 80 49",
                           "Possibly the best restaurant in , Platan has a classy interior and a summer terrace for shady al fresco dining in the summer. The menu includes some Middle Eastern and Thai influences alongside regional dishes like Russian-style red caviar or cooling Uzbek chalop (cucumber, dill, green onion and sour cream soup).\n" +
                                   "\nThe salad menu is particularly good: try the excellent lobio (a Georgian bean, walnut, garlic, lemon and parsley salad), the Thai beef salad or the zingy Bloody Mary salad. Reservations are advised for dinner."));
           restaurants.add(
                   new Restaurants(1,
                           "Old city",
                           "International",
                           R.drawable.tore,
                           "Hours : 10AM - 11PM\nPrice  : mains 14,000 - 16,000 \nTel : (+913)93 346 80 20",
                           "This charming place in the Russian part of town is recommended for its interesting dishes, such as basturma cold smoked beef, lavash (flat bread) with feta-like brinza cheese, and over 40 salads, including a delicious beetroot and walnut option. Service is friendly and assured, the classy interior has a cosy fireplace, and while it caters largely to tourists, standards are high."));
       }

    }
