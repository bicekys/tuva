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
import ru.example.tuva.travel.Model.Sights;
import ru.example.tuva.travel.SightsInnerActivity;

public class SightsViewAdapter extends RecyclerView.Adapter<SightsViewAdapter.SightsViewHolder> {
    private static final String TAG = "SightsViewAdapter";

    private Context mCtx;
    private List<Sights> sightsList;

    public SightsViewAdapter(Context mCtx, List<Sights> sightsList) {
        this.mCtx = mCtx;
        this.sightsList = sightsList;
    }

    @NonNull
    @Override
    public SightsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_sights, null);
        return new SightsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SightsViewHolder sightsViewHolder, final int i) {
        final Sights sights = sightsList.get(i);

        sightsViewHolder.sightsTitle.setText(sights.getSights_title());
        sightsViewHolder.imageView.setImageResource(sights.getSights_bg());
        sightsViewHolder.imageView.setColorFilter(0xfff0f0f0, PorterDuff.Mode.MULTIPLY);
        sightsViewHolder.sightsDesc.setText(sights.getSights_desc());


        sightsViewHolder.sightsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on "+sights.getSights_title());
                Toast.makeText(mCtx, "Clicked on "+sights.getSights_title(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mCtx, SightsInnerActivity.class);
                intent.putExtra("sights_bg", sights.getSights_bg());
                intent.putExtra("sights_title", sights.getSights_title());
                intent.putExtra("sights_desc", sights.getSights_desc());
                intent.putExtra("sights_details", sights.getSights_details());
                intent.putExtra("sights_full_desc", sights.getSights_fullDesc());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return sightsList.size(); }

    class SightsViewHolder extends RecyclerView.ViewHolder{
        TextView sightsTitle;
        ImageView imageView;
        TextView sightsDesc;
        CardView sightsCardView;
        TextView sightsDetails;
        TextView sightsFullDesc;


        public SightsViewHolder(@NonNull View itemView) {
            super(itemView);
            //cards
            sightsTitle = itemView.findViewById(R.id.sights_title);
            imageView = itemView.findViewById(R.id.sights_bg);
            sightsDesc = itemView.findViewById(R.id.sights_card_desc);
            sightsCardView = itemView.findViewById(R.id.sights_cardView);
            sightsDetails = itemView.findViewById(R.id.sights_inner_details_text);
            sightsFullDesc = itemView.findViewById(R.id.expandable_text);
        }
    }

}