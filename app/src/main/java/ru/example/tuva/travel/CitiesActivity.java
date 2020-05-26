package ru.example.tuva.travel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import ru.example.tuva.travel.Adapters.CitiesViewAdapter;
import ru.example.tuva.travel.Model.Cities;

public class CitiesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CitiesViewAdapter adapter;
    List<Cities> citiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        recyclerView = findViewById(R.id.citiesRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        citiesList = new ArrayList<>();
        citiesList.add(
                new Cities(
                        0,
                        "Торе-Холь",
                        R.drawable.torehol,
                        "Длина озера — около 16 км, ширина — 3-4 км, глубина — 4-5 м. высота над уровнем моря — 1148 м. Само озеро и окружающие его берега как природный комплекс уникальны не только для Убсунурской котловины, но и для всей территории Тувы. В отличие от соседних соленых озер — Бай-Холь, Дус-Холь и другие — оно пресноводное и подпитывается родниками, вытекающими из барханных песков. В середине июля вода прогревается до +22 градусов.\n" +
                                "\nОзеро Торе-Холь — это своеобразный экологический оазис, здесь множество водоплавающих и околоводных птиц. Песчаные массивы содержат большое количество редких исчезающих животных и растений, особенно в прибрежной части озера, где отмечены растения-эндемики.\\n\" +" +
                                "\n В озере обитают представитель карповых рыб — алтайский осман, эндемик водоемов Центральной Азии, также щука. В озеро, как в зеркало, смотрятся плывущие над землей облака, высокие барханы окружающих его песков, ивы и тополя, подступающие иногда к самой воде. "));

        citiesList.add(
                new Cities(
                        1,
                        "Азас",
                        R.drawable.azas,
                        "Central Asia’s holiest city, Bukhara (Buxoro) has buildings spanning a thousand years of history, and a thoroughly lived-in and cohesive old centre that hasn’t changed too much in two centuries. It is one of the best places in Central Asia for a glimpse of pre-Russian Turkestan.\n" +
                                "\nMost of the centre is an architectural preserve, full of medressas and minarets, a massive royal fortress and the remnants of a once-vast market complex. Government restoration efforts have been more subtle and less indiscriminate than in flashier Samarkand. The city’s accommodation options are by far the best and most atmospheric in the country.\n" +
                                "\nYou’ll need at least two days to see the main sights. Try to allow time to lose yourself in the old town; it’s easy to overdose on the 140-odd protected buildings and miss the whole for its many parts."));
        citiesList.add(
                new Cities(
                        2,
                        "Ногаан Хол",
                        R.drawable.nogaanxol,
                        "No name is as evocative of the Silk Road as Samarkand (Samarqand). For most people it has the mythical resonance of Zanzibar or Timbuktu, fixed in the Western popular imagination by imaginative poets and playwrights, few of whom saw the city in the flesh.\n\n" +
                                "On the ground the sublime, larger-than-life monuments of Timur (Tamerlane) and the city’s long, rich history still work some kind of magic. You can visit most of Samarkand’s high-profile attractions in two or three days. If you’re short on time, at least see the Registan, Gur-e-Amir, Bibi-Khanym Mosque and Shah-i-Zinda.\n\n" +
                                "Away from these islands of majesty, Samarkand is a well-groomed modern city, with a large Russian town of broad avenues and parks. The recent walling off of parts of the old town and the pedestrianisation of Toshkent street has led to the 'Disneyfication' of some areas, but there's enough grandeur left to say that Samarkand remains a breathtaking place to visit."));

        citiesList.add(
                new Cities(
                        3,
                        "Алдын-Булак",
                        R.drawable.aldyn,
                        "No name is as evocative of the Silk Road as Samarkand (Samarqand). For most people it has the mythical resonance of Zanzibar or Timbuktu, fixed in the Western popular imagination by imaginative poets and playwrights, few of whom saw the city in the flesh.\n\n" +
                                "On the ground the sublime, larger-than-life monuments of Timur (Tamerlane) and the city’s long, rich history still work some kind of magic. You can visit most of Samarkand’s high-profile attractions in two or three days. If you’re short on time, at least see the Registan, Gur-e-Amir, Bibi-Khanym Mosque and Shah-i-Zinda.\n\n" +
                                "Away from these islands of majesty, Samarkand is a well-groomed modern city, with a large Russian town of broad avenues and parks. The recent walling off of parts of the old town and the pedestrianisation of Toshkent street has led to the 'Disneyfication' of some areas, but there's enough grandeur left to say that Samarkand remains a breathtaking place to visit."));

        adapter = new CitiesViewAdapter(this, citiesList);
        recyclerView.setAdapter(adapter);
    }
}
