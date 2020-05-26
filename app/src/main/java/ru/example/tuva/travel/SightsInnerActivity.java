package ru.example.tuva.travel;

import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class SightsInnerActivity extends AppCompatActivity {
    private static final String TAG = "SightsInnerActivity";
    ExpandableTextView expandableTextView;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights_inner);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            int sightsBG = bundle.getInt("sights_bg");
            String sightsTitle = bundle.getString("sights_title");
            String sightsDesc = bundle.getString("sights_desc");
            String sightsDetails = bundle.getString("sights_details");
            String sightsFullDesc = bundle.getString("sights_full_desc");

            setImage(sightsBG, sightsTitle, sightsDesc, sightsDetails, sightsFullDesc);
        }
    }

    private void setImage(int sightsBG, String sightsTitle, String sightsDesc, String sightsDetails, String sightsFullDesc){
        ImageView sightsInnerImageView =  findViewById(R.id.sights_inner_image);
        sightsInnerImageView.setImageResource(sightsBG);

        TextView sightsInnerTitle = findViewById(R.id.sights_inner_title);
        sightsInnerTitle.setText(sightsTitle);

        TextView sightsInnerDesc = findViewById(R.id.sights_inner_desc);
        sightsInnerDesc.setText(sightsDesc);

        TextView sightsInnerDetails= findViewById(R.id.sights_inner_details_text);
        sightsInnerDetails.setText(sightsDetails);

        expandableTextView = findViewById(R.id.expandable_text_view);
        expandableTextView.setText(sightsFullDesc);

        imageButton = findViewById(R.id.expand_collapse);
        imageButton.setColorFilter(0xff78849E, PorterDuff.Mode.SRC_ATOP);
    }
}
