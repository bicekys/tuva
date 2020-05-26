package ru.example.tuva.travel.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import ru.example.tuva.travel.MenuActivities.MainMapActivity;
import ru.example.tuva.travel.MenuActivities.SecondMapActivity;
import ru.example.tuva.travel.MenuActivities.ToreHolMapActivity;
import ru.example.tuva.travel.Model.NavButtons;
import ru.example.tuva.travel.R;
import ru.example.tuva.travel.MenuActivities.RestaurantsActivity;
import ru.example.tuva.travel.MenuActivities.SightsActivity;

public class NavButtonsViewAdapter extends RecyclerView.Adapter<NavButtonsViewAdapter.NavButtonsViewHolder> {
    private static final String TAG = "NavButtonsViewAdapter";

    //vars
    private Context navBtnContext;
    private List<NavButtons> navButtonsList;

    public NavButtonsViewAdapter(Context navBtnContext, List<NavButtons> navButtonsList) {
        this.navBtnContext = navBtnContext;
        this.navButtonsList = navButtonsList;
    }

    @NonNull
    @Override
    public NavButtonsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(navBtnContext).inflate(R.layout.layout_nav_buttons, viewGroup, false);
        return new NavButtonsViewAdapter.NavButtonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavButtonsViewHolder navButtonsViewHolder, int i) {
        final NavButtons navButtons = navButtonsList.get(i);

        navButtonsViewHolder.navBtnImageView.setImageResource(navButtons.getBtn_image());
        navButtonsViewHolder.btnName.setText(navButtons.getBtn_name());

        navButtonsViewHolder.btnCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(navBtnContext, "Clicked on "+navButtons.getBtn_name(), Toast.LENGTH_SHORT).show();

            if (navButtons.getBtn_name().equals("Места")){
                Intent intent = new Intent(navBtnContext, SightsActivity.class);
                intent.putExtra("sights_city_title", navButtons.getBtn_city_title());
                navBtnContext.startActivity(intent);
            }
            if (navButtons.getBtn_name().equals("Базы отдыха")){
                Intent intent = new Intent(navBtnContext, RestaurantsActivity.class);
                intent.putExtra("restaurants_city_title", navButtons.getBtn_city_title());
                navBtnContext.startActivity(intent);
            }
            if (navButtons.getBtn_name().equals("Карта")){
                Intent intent = new Intent(navBtnContext, MainMapActivity.class);
                intent.putExtra("map_city_title", navButtons.getBtn_city_title());
                navBtnContext.startActivity(intent);
            }
                if (navButtons.getBtn_name().equals("Торе-Холь")){
                    Intent intent = new Intent(navBtnContext, SecondMapActivity.class);
                    intent.putExtra("map_city_title", navButtons.getBtn_city_title());
                    navBtnContext.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return navButtonsList.size();
    }

    class NavButtonsViewHolder extends RecyclerView.ViewHolder{
        ImageView navBtnImageView;
        TextView btnName;
        CardView btnCardView;
        TextView btnCityTitle;
        TextView btnRestaurantsCityTitle;

        public NavButtonsViewHolder(@NonNull View itemView) {
            super(itemView);
            navBtnImageView = itemView.findViewById(R.id.nav_btn);
            btnName = itemView.findViewById(R.id.btn_name);
            btnCardView = itemView.findViewById(R.id.btn_cardView);
            btnCityTitle = itemView.findViewById(R.id.sights_desc);
            btnRestaurantsCityTitle = itemView.findViewById(R.id.restaurants_city);
        }
    }
}
