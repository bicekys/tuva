package ru.example.tuva.travel;

import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class RestaurantsInnerActivity extends AppCompatActivity {
    private static final String TAG = "RestaurantsInnerActivit";
    ExpandableTextView expandableTextView;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_inner);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            int restaurantsBG = bundle.getInt("restaurants_bg");
            String restaurantsTitle = bundle.getString("restaurants_title");
            String restaurantsDesc = bundle.getString("restaurants_desc");
            String restaurantsDetails = bundle.getString("restaurants_details");
            String restaurantsFullDesc = bundle.getString("restaurants_full_desc");

            setImage(restaurantsBG, restaurantsTitle, restaurantsDesc, restaurantsDetails, restaurantsFullDesc);
        }
    }

    private void setImage(int restaurantsBG, String restaurantsTitle, String restaurantsDesc, String restaurantsDetails, String restaurantsFullDesc){
        ImageView sightsInnerImageView =  findViewById(R.id.restaurants_inner_image);
        sightsInnerImageView.setImageResource(restaurantsBG);

        TextView sightsInnerTitle = findViewById(R.id.restaurants_inner_title);
        sightsInnerTitle.setText(restaurantsTitle);

        TextView sightsInnerDesc = findViewById(R.id.restaurants_inner_desc);
        sightsInnerDesc.setText(restaurantsDesc);

        TextView sightsInnerDetails= findViewById(R.id.restaurants_inner_details_text);
        sightsInnerDetails.setText(restaurantsDetails);

        expandableTextView = findViewById(R.id.expandable_text_view);
        expandableTextView.setText(restaurantsFullDesc);

        imageButton = findViewById(R.id.expand_collapse);
        imageButton.setColorFilter(0xff78849E, PorterDuff.Mode.SRC_ATOP);
    }
}
