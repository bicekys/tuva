package ru.example.tuva.travel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.example.tuva.travel.R;
import ru.example.tuva.travel.Model.Restaurants;
import ru.example.tuva.travel.RestaurantsInnerActivity;


public class RestaurantsViewAdapter extends RecyclerView.Adapter<RestaurantsViewAdapter.RestaurantsViewHolder> {
    private static final String TAG = "SightsViewAdapter";

    private Context mCtx;
    private List<Restaurants> restaurantsList;

    public RestaurantsViewAdapter(Context mCtx, List<Restaurants> restaurantsList) {
        this.mCtx = mCtx;
        this.restaurantsList = restaurantsList;
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_restaurants, null);
        return new RestaurantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder restaurantsViewHolder, final int i) {
        final Restaurants restaurants = restaurantsList.get(i);

        restaurantsViewHolder.restaurantsImageView.setImageResource(restaurants.getRestaurants_bg());
        restaurantsViewHolder.restaurantsImageView.setColorFilter(0xfff0f0f0, PorterDuff.Mode.MULTIPLY);
        restaurantsViewHolder.restaurantsTitle.setText(restaurants.getRestaurants_title());
        restaurantsViewHolder.restaurantsDesc.setText(restaurants.getRestaurants_desc());



        restaurantsViewHolder.restaurantsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on "+restaurants.getRestaurants_title());
                Toast.makeText(mCtx, "Clicked on "+restaurants.getRestaurants_title(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mCtx, RestaurantsInnerActivity.class);
                intent.putExtra("restaurants_bg", restaurants.getRestaurants_bg());
                intent.putExtra("restaurants_title", restaurants.getRestaurants_title());
                intent.putExtra("restaurants_desc", restaurants.getRestaurants_desc());
                intent.putExtra("restaurants_details", restaurants.getRestaurants_details());
                intent.putExtra("restaurants_full_desc", restaurants.getRestaurants_fullDesc());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return restaurantsList.size(); }

    class RestaurantsViewHolder extends RecyclerView.ViewHolder{
        TextView restaurantsTitle;
        ImageView restaurantsImageView;
        TextView restaurantsDesc;
        CardView restaurantsCardView;


        public RestaurantsViewHolder(@NonNull View itemView) {
            super(itemView);
            //cards
            restaurantsImageView = itemView.findViewById(R.id.restaurants_bg);
            restaurantsTitle = itemView.findViewById(R.id.restaurants_title);
            restaurantsDesc = itemView.findViewById(R.id.restaurants_desc);
            restaurantsCardView = itemView.findViewById(R.id.restaurants_cardView);
        }
    }
}