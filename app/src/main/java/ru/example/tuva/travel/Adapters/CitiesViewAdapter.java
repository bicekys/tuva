package ru.example.tuva.travel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

import ru.example.tuva.travel.CitiesInnerActivity;
import ru.example.tuva.travel.Model.Cities;
import ru.example.tuva.travel.R;

import static android.content.ContentValues.TAG;

public class CitiesViewAdapter extends RecyclerView.Adapter<CitiesViewAdapter.CitiesViewHolder> {

    private Context mCtx;
    private List<Cities> citiesList;

    public CitiesViewAdapter(Context mCtx, List<Cities> citiesList) {
        this.mCtx = mCtx;
        this.citiesList = citiesList;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_cities_cardview, null);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder citiesViewHolder, final int i) {
        final Cities cities = citiesList.get(i);

        citiesViewHolder.textViewTitle.setText(cities.getCard_title());
        citiesViewHolder.imageView.setImageResource(cities.getCard_bg());
        citiesViewHolder.imageView.setColorFilter(0xfff5f5f5, PorterDuff.Mode.MULTIPLY);

        citiesViewHolder.pLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on:"+cities.getCard_title());

                Intent intent = new Intent(mCtx, CitiesInnerActivity.class);
                intent.putExtra("city_bg", cities.getCard_bg());
                intent.putExtra("city_title", cities.getCard_title());
                intent.putExtra("city_desc", cities.getCity_desc());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    class CitiesViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        ImageView imageView;
        RelativeLayout pLayout;

        TextView cityDesc;
        ImageView cityImage;

        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            //cards
            textViewTitle = itemView.findViewById(R.id.card_city);
            imageView = itemView.findViewById(R.id.card_bg);
            pLayout = itemView.findViewById(R.id.pLayout);
            cityDesc = itemView.findViewById(R.id.expandable_text);
            cityImage = itemView.findViewById(R.id.city_image);
        }
    }

}