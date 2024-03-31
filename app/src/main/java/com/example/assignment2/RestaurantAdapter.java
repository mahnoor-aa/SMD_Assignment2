package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    ArrayList<RestaurantInfo> restaurants;
    public RestaurantAdapter(ArrayList<RestaurantInfo> list)
    {
        restaurants=list;
    }

    public void setRestaurant(ArrayList<RestaurantInfo> res) {
        this.restaurants = res;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_res,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RestaurantInfo res=restaurants.get(position);
        holder.tvName.setText(res.getName());
        holder.tvLocation.setText(res.getLocation());
        holder.tvPhone.setText(res.getPhone());
        holder.tvRating.setText(res.getRating());
        holder.tvInfo.setText(res.getDesc());

        holder.tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=res.getPhone();
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tvPhone, tvRating, tvInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvInfo = itemView.findViewById(R.id.tvInfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), tvName.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
