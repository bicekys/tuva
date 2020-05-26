package ru.example.tuva.travel.modules;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.example.tuva.travel.R;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);
        Button backToMap;

        backToMap = findViewById(R.id.btnBack);

        backToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            String markerTitle = bundle.getString("marker_title");
            setTitle(markerTitle);
        }
    }

    public void setTitle(String title) {
        TextView marker_Title;
        marker_Title = findViewById(R.id.marker_title);
        marker_Title.setText(title);
    }
}
