package ru.example.tuva.travel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import ru.example.tuva.travel.MenuActivities.ExperiencesActivity;
import ru.example.tuva.travel.Model.Experiences;
import ru.example.tuva.travel.R;

public class ExperiencesViewAdapter extends RecyclerView.Adapter<ExperiencesViewAdapter.ExperiencesViewHolder>{
    private static final String TAG = "ExperiencesViewAdapter";

    //vars
    private Context expContext;
    private List<Experiences> experiencesList;

    public ExperiencesViewAdapter(Context expContext, List<Experiences> experiencesList) {
        this.expContext = expContext;
        this.experiencesList = experiencesList;
    }

    @NonNull
    @Override
    public ExperiencesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(expContext).inflate(R.layout.layout_top_experiences, viewGroup, false);
        return new ExperiencesViewAdapter.ExperiencesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperiencesViewHolder experiencesViewHolder, int i) {
        final Experiences experiences = experiencesList.get(i);

        experiencesViewHolder.expImageView.setImageResource(experiences.getExp_image());
        experiencesViewHolder.expImageView.setColorFilter(0xffAEAEAE, PorterDuff.Mode.MULTIPLY);

        experiencesViewHolder.expCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(expContext, ExperiencesActivity.class);
                intent.putExtra("exp_bg", experiences.getExp_image());
                expContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return experiencesList.size();
    }

    class ExperiencesViewHolder extends RecyclerView.ViewHolder{
        ImageView expImageView;
        TextView expTitle;
        TextView expDesc;
        TextView expFullDesc;
        CardView expCardView;
        TextView expDetails;

        public ExperiencesViewHolder(@NonNull View itemView) {
            super(itemView);
            expImageView = itemView.findViewById(R.id.experience_bg);
            expTitle = itemView.findViewById(R.id.experience_title);
            expDesc = itemView.findViewById(R.id.experience_desc);
            expFullDesc = itemView.findViewById(R.id.expandable_text);
            expCardView = itemView.findViewById(R.id.experience_cardView);
            expDetails = itemView.findViewById(R.id.details_text);
        }
    }
}
