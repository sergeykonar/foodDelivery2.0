package com.example.kondadeliveryapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kondadeliveryapp.MenuActivity;
import com.example.kondadeliveryapp.R;
import com.example.kondadeliveryapp.models.Restaurant;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {


    private List<Restaurant> restaurantList;

    private Context context;
    public RestaurantsAdapter(List<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsAdapter.MyViewHolder holder, int position) {

        holder.restaurantName.setText(restaurantList.get(position).getName());
        String str = "<b>Address: </b><br/>"+restaurantList.get(position).getAddress();
        holder.restaurantAddress.setText(Html.fromHtml(str));
        String sourceString = "<b>" + "Today's hours: " + "</b> " +"<br/>" +  restaurantList.get(position).getHours().getTodaysHours();
        holder.restaurantHours.setText(Html.fromHtml(sourceString) );

        Glide.with(holder.thumbImage)
                .load(restaurantList.get(position).getImage())
                .placeholder(R.drawable.ic_baseline_favorite_24)
                .dontAnimate()
                .into(holder.thumbImage);


        holder.cardView.setOnClickListener(cardListener);
        holder.cardView.setTag(position);

    }



    private final View.OnClickListener cardListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int clickedPosition = (int) v.getTag();
//            NavDirections action =
//                    HomeFragmentDirections.actionHomeFragmentToMenuActivity();
            Bundle bundle = new Bundle();

            bundle.putInt("id", clickedPosition);
            Intent i = new Intent(context, MenuActivity.class);
            i.putExtras(bundle);
            context.startActivity(i);
//            Navigation.findNavController(v).navigate(action);
        }
    };

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  restaurantName;
        TextView  restaurantAddress;
        TextView restaurantHours;
        ImageView thumbImage;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            restaurantName = view.findViewById(R.id.restaurantName);
            restaurantAddress = view.findViewById(R.id.restaurantAddress);
            restaurantHours = view.findViewById(R.id.restaurantHours);
            thumbImage = view.findViewById(R.id.thumbImage);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
