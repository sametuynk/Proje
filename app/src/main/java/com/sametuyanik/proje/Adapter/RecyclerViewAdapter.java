package com.sametuyanik.proje.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sametuyanik.proje.Glide.ImageLoader;
import com.sametuyanik.proje.Model.FoodModel;
import com.sametuyanik.proje.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Yemekholder> {
    private ArrayList<FoodModel> foodModels;
    private Context context;

    public RecyclerViewAdapter(ArrayList<FoodModel> foodModels,Context context) {
        this.foodModels = foodModels;
        this.context = context;
    }

    @NonNull
    @Override
    public Yemekholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.yemek_list,parent,false);
        return new Yemekholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Yemekholder holder, int position) {
        holder.textTitle.setText(foodModels.get(position).title);
        ImageLoader.load(context,foodModels.get(position).getImage(),holder.imageView);

    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }

    public class Yemekholder extends RecyclerView.ViewHolder {
        TextView textTitle;
        ImageView imageView;
        public Yemekholder(@NonNull View itemView) {
            super(itemView);
            textTitle=itemView.findViewById(R.id.yemekismi);
            imageView=itemView.findViewById(R.id.yemekImage);

        }

    }
}
