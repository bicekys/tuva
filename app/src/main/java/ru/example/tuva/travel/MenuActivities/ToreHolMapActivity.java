package ru.example.tuva.travel.MenuActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ru.example.tuva.travel.R;

public class ToreHolMapActivity extends AppCompatActivity implements /*GoogleMap.OnMarkerClickListener,*/
        OnMapReadyCallback {

    private static final LatLng TOREHOL = new LatLng(50.0613266, 95.08083344);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tore_hol_map);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_dot_place);

        Marker mTillya = map.addMarker(new MarkerOptions()
                .position(TOREHOL)
                .icon(icon)
                .title("Озеро Торе-Холь")
                .snippet("Торе-Холь, Сарыг-Булунский сумон, Erzinsky Kozhuun, Tuva Republic"));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
