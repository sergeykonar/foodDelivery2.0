package com.example.kondadeliveryapp.adapters;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kondadeliveryapp.MainActivity;
import com.example.kondadeliveryapp.R;
import com.example.kondadeliveryapp.models.CartItem;
import com.example.kondadeliveryapp.models.Item;
import com.example.kondadeliveryapp.viewmodels.CartViewModel;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> items;
    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.tv.setText(items.get(position).getName());
        Glide.with(holder.imageView)
                .load(items.get(position).getUrl())
                .placeholder(R.drawable.ic_baseline_favorite_24)
                .dontAnimate()
                .into(holder.imageView);

        holder.price.setText(String.valueOf(items.get(position).getPrice()));
        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(l);
    }

    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int clickedPosition = (int) v.getTag();

            CartItem cartItem = new CartItem(items.get(clickedPosition).getName(), items.get(clickedPosition).getPrice(), items.get(clickedPosition).getUrl());
            MainActivity.cartViewModel.insertItem(cartItem);
        }
    };

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView tv;
        private ImageView imageView;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.itemCard);
            tv=itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView2);
            price = itemView.findViewById(R.id.itemPrice);
        }


    }
}