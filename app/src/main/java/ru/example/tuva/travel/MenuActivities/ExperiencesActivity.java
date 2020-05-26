package ru.example.tuva.travel.MenuActivities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import ru.example.tuva.travel.R;

public class ExperiencesActivity extends AppCompatActivity {
    private static final String TAG = "ExperiencesActivity";
    ExpandableTextView expandableTextView;
    ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiences);
        Log.d(TAG, "onCreate: started.");
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            int expBg = bundle.getInt("exp_bg");
            String expTitle = bundle.getString("exp_title");
            String expDesc = bundle.getString("exp_desc");
            String expFullDesc = bundle.getString("exp_full_desc");
            String expDetails = bundle.getString("exp_details");

            setImage(expBg, expTitle, expDesc, expFullDesc, expDetails);
        }
    }

    private void setImage(int expBg, String expTitle, String expDesc, String expFullDesc, String expDetails){
        Log.d(TAG, "setImage: setting image and name to widgets.");

        ImageView exp_image = findViewById(R.id.exp_image);
        exp_image.setImageResource(expBg);
        exp_image.setColorFilter(0xffAEAEAE, PorterDuff.Mode.MULTIPLY);


        TextView exp_title = findViewById(R.id.exp_title);
        exp_title.setText(expTitle);

        TextView exp_desc = findViewById(R.id.exp_desc);
        exp_desc.setText(expDesc);

        TextView details = findViewById(R.id.details_text);
        details.setText(expDetails);

        expandableTextView = findViewById(R.id.expandable_text_view);
        expandableTextView.setText(expFullDesc);

        imageButton = findViewById(R.id.expand_collapse);
        imageButton.setColorFilter(0xff78849E, PorterDuff.Mode.SRC_ATOP);
    }
}
