package ru.example.tuva.travel;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.Arrays;

import ru.example.tuva.travel.MenuActivities.MainMapActivity;
import ru.example.tuva.travel.UsersActivities.ChatActivity;
import ru.example.tuva.travel.UsersActivities.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private LinearLayout NavGridBar;
    ImageButton navButton;
    private SearchView searchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private RelativeLayout top_bar_relative;
    private LinearLayout main_bg;
    private Button citiesBtn;
    private TextView title_uzb;
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavGridBar = findViewById(R.id.NavGridBar);
        searchView = findViewById(R.id.searchView);
        top_bar_relative = findViewById(R.id.tool_bar_layout);
        navButton = findViewById(R.id.navButton);
        main_bg = findViewById(R.id.main_bg);
        citiesBtn = findViewById(R.id.citiesBtn);
        title_uzb = findViewById(R.id.title_tuva);

        CardView cvVideos = findViewById(R.id.btnVideos);
        CardView cvAboutUs = findViewById(R.id.btnAboutUs);

        //Change color of text and hint text
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.white));
       // Change the underline color
        View searchplate = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        searchplate.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        searchView.setMaxWidth(600);

        cvVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        cvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        if (isServiceOk()) {
            init();


        }

        citiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CitiesActivity.class);
                startActivity(intent);
            }
        });


        SearchList();
    }

    private void init() {
        CardView cvMap = findViewById(R.id.btnMap);
        cvMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMapActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean isVisible = true;

    public void navBarShowUp(View view) {
        if (isVisible) {
            NavGridBar.setVisibility(View.VISIBLE);
            NavGridBar.animate().alpha(1.0f).setDuration(100);
            top_bar_relative.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
            top_bar_relative.animate().alpha(1.0f).setDuration(100);

            main_bg.getBackground().setColorFilter(0xff959595, PorterDuff.Mode.MULTIPLY);
            citiesBtn.getBackground().setColorFilter(0xff959595, PorterDuff.Mode.MULTIPLY);
            citiesBtn.setTextColor(0xff959595);
            title_uzb.setTextColor(0xff959595);
            isVisible = false;
        } else {
            NavGridBar.setVisibility(View.INVISIBLE);
            NavGridBar.animate().alpha(0.8f).setDuration(100);
            top_bar_relative.setBackgroundColor(Color.TRANSPARENT);
            top_bar_relative.animate().alpha(1.0f).setDuration(500);

            main_bg.getBackground().setColorFilter(0xffffffff, PorterDuff.Mode.MULTIPLY);
            citiesBtn.getBackground().setColorFilter(0xffffffff, PorterDuff.Mode.MULTIPLY);
            title_uzb.setTextColor(0xffffffff);
            citiesBtn.setTextColor(0xffffffff);
            isVisible = true;
        }
    }

    public boolean isServiceOk() {

        Log.d(TAG, "isServiceOk: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map reques
            Log.d(TAG, "isServiceOk: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "is ServiceOk: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can`t make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @SuppressLint("RestrictedApi")
    public void SearchList() {
        ArrayList<String> arraySearch = new ArrayList<>();
        final ArrayAdapter<String> adapter;
        arraySearch.addAll(Arrays.asList(getResources().getStringArray(R.array.searchArray)));

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arraySearch);

        mSearchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        mSearchAutoComplete.setDropDownBackgroundResource(R.color.white);
        mSearchAutoComplete.setDropDownAnchor(R.id.searchView);
        mSearchAutoComplete.setThreshold(1);
        mSearchAutoComplete.setAdapter(adapter);

        final View dropDownAnchor = searchView.findViewById(mSearchAutoComplete.getDropDownAnchor());
        if (dropDownAnchor != null) {
            dropDownAnchor.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {

                    // calculate width of DropdownView
                    int point[] = new int[2];
                    dropDownAnchor.getLocationOnScreen(point);
                    // x coordinate of DropDownView
                    int dropDownPadding = point[0] + mSearchAutoComplete.getDropDownHorizontalOffset();

                    Rect screenSize = new Rect();
                    getWindowManager().getDefaultDisplay().getRectSize(screenSize);
                    // screen width
                    int screenWidth = screenSize.width();

                    // set DropDownView width
                    mSearchAutoComplete.setDropDownWidth(screenWidth - dropDownPadding * 2);
                    Log.e(TAG, "DropDownAnchor: " + mSearchAutoComplete.getDropDownAnchor());

                }
            });

            searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {//поисковик
                @Override
                public boolean onSuggestionSelect(int i) {

                    return false;
                }

                public boolean onSuggestionClick(int i) {
                    String selectedItem = (String) adapter.getItem(i);
                    Log.d(TAG, "onSuggestionClick: SearchView result: " + selectedItem);

                    Intent intent = new Intent(MainActivity.this, CitiesInnerActivity.class);
                    if (selectedItem.equals("Торе-Холь")) {
                        int background = R.drawable.fon;
                        String title = "Торе-Холь";
                        String description = "Длина озера — около 16 км, ширина — 3-4 км, глубина — 4-5 м. высота над уровнем моря — 1148 м. Само озеро и окружающие его берега как природный комплекс уникальны не только для Убсунурской котловины, но и для всей территории Тувы. В отличие от соседних соленых озер — Бай-Холь, Дус-Холь и другие — оно пресноводное и подпитывается родниками, вытекающими из барханных песков. В середине июля вода прогревается до +22 градусов.\\n\\n\" +\n" +
                                "                                \"Озеро Торе-Холь — это своеобразный экологический оазис, здесь множество водоплавающих и околоводных птиц. Песчаные массивы содержат большое количество редких исчезающих животных и растений, особенно в прибрежной части озера, где отмечены растения-эндемики.\\n\\n\" +\n" +
                                "                                \"В озере обитают представитель карповых рыб — алтайский осман, эндемик водоемов Центральной Азии, также щука. В озеро, как в зеркало, смотрятся плывущие над землей облака, высокие барханы окружающих его песков, ивы и тополя, подступающие иногда к самой воде.    ";
                        intent.putExtra("city_bg", background);
                        intent.putExtra("city_title", title);
                        intent.putExtra("city_desc", description);

                        startActivity(intent);
                    }
                    return true;
                }
            });

        }
    }
}
