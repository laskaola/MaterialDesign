package com.example.materialdesign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.card.MaterialCardView;

import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private FragmentActivity mFragmentActivity;
    private List<Map<String, String>> dataPackage;
    private ArubaFragment aruba;



    public RecyclerViewAdapter(FragmentActivity fragmentActivity, Context context, List<Map<String, String>> list) {
        mContext = context;
        mFragmentActivity = fragmentActivity;
        dataPackage = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.island_fragment, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        holder.title.setText(dataPackage.get(position).get("TITLE"));
        holder.image.setImageResource(Integer.parseInt(dataPackage.get(position).get("IMAGE")));


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeFragment.beginTransaction();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataPackage.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;
        private MaterialCardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.img);
            card = itemView.findViewById(R.id.card);
        }
    }



}
