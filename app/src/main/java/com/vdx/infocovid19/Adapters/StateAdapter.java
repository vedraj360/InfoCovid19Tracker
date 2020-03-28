package com.vdx.infocovid19.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vdx.infocovid19.Models.Statewise;
import com.vdx.infocovid19.R;
import com.vdx.infocovid19.Utils.FontChangeCrawler;
import com.vdx.infocovid19.Utils.Helper;

import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> implements Filterable {

    private ArrayList<Statewise> statewiseArrayList;
    private ArrayList<Statewise> statewiseFilteredList;
    private Context context;
    private setOnClickListener clickListener;

    public StateAdapter(ArrayList<Statewise> statewiseArrayList, Context context, setOnClickListener clickListener) {
        this.statewiseArrayList = statewiseArrayList;
        this.context = context;
        this.clickListener = clickListener;
        this.statewiseFilteredList = new ArrayList<>(statewiseArrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_item, parent, false);
        FontChangeCrawler fontChanger = new FontChangeCrawler(context.getAssets(), "ar.ttf");
        fontChanger.replaceFonts((ViewGroup) view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Statewise statewise = statewiseArrayList.get(position);

        //holder.info_card.setAnimation(AnimationUtils.loadAnimation(context, R.anim.up_bottom_transition_animation));/
        holder.expandable_view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));

        holder.state_name.setText(statewise.getState());

        boolean isExpanded = statewise.isExpanded();
        holder.expandable_view.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        holder.confirmed_current.setText(String.valueOf(statewise.getConfirmed()));
        holder.active_current.setText(String.valueOf(statewise.getActive()));
        holder.recovered_current.setText(String.valueOf(statewise.getRecovered()));
        holder.dead_current.setText(String.valueOf(statewise.getDeaths()));
        if (statewise.getDiff() > 0) {
            holder.increased_image.setVisibility(View.VISIBLE);
            holder.confirmed_increased.setText(String.valueOf(statewise.getDiff()));
        } else if (statewise.getDiff() < 0) {
            holder.increased_image.setImageResource(R.drawable.down);
            holder.confirmed_increased.setText(String.valueOf(statewise.getDiff()));
        } else {
            holder.increased_image.setVisibility(View.GONE);
            holder.confirmed_increased.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return statewiseArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            ArrayList<Statewise> filteredList = new ArrayList<>();

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint.toString().isEmpty()) {
                    filteredList.addAll(statewiseFilteredList);
                } else {
                    for (Statewise statewise : statewiseFilteredList) {
                        if (statewise.getState().toLowerCase().trim().contains(constraint.toString().toLowerCase())) {
                            filteredList.add(statewise);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                statewiseArrayList.clear();
                statewiseArrayList.addAll((ArrayList<Statewise>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView state_name, confirmed_current, active_current, recovered_current, dead_current, confirmed_increased;
        LinearLayout expandable_view;
        ImageView increased_image;
        LinearLayout info_card;

        ViewHolder(@NonNull final View view) {
            super(view);
            expandable_view = view.findViewById(R.id.expandable_view);

            state_name = view.findViewById(R.id.state_name);
            confirmed_current = view.findViewById(R.id.confirmed_current);
            active_current = view.findViewById(R.id.active_current);
            recovered_current = view.findViewById(R.id.recovered_current);
            dead_current = view.findViewById(R.id.dead_current);
            confirmed_increased = view.findViewById(R.id.confirmed_increased);
            increased_image = view.findViewById(R.id.increased_image);
            info_card = view.findViewById(R.id.info_card);

            setTextFont(view);

            state_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Statewise statewise = statewiseArrayList.get(getAdapterPosition());
                    if (clickListener == null) return;
                    clickListener.onClick(getAdapterPosition(), statewise, view);
                }
            });
        }

        private void setTextFont(View view) {
            TextView confirmed = view.findViewById(R.id.confirmed_status);
            TextView active = view.findViewById(R.id.active_status);
            TextView recovered = view.findViewById(R.id.recovered_status);
            TextView dead = view.findViewById(R.id.dead_status);

            confirmed.setTypeface(Helper.getFontSb(context));
            active.setTypeface(Helper.getFontSb(context));
            recovered.setTypeface(Helper.getFontSb(context));
            dead.setTypeface(Helper.getFontSb(context));

        }


    }


    public interface setOnClickListener {
        public void onClick(int position, Statewise statewise, View view);
    }


}
