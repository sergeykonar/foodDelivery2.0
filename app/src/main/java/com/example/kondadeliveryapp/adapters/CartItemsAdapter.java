package com.example.kondadeliveryapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kondadeliveryapp.R;
import com.example.kondadeliveryapp.models.CartItem;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {

    private List<CartItem> cartItemList;

    public CartItemsAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
        Log.e(String.valueOf(cartItemList.size()), "f");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(cartItemList.get(position).getName());

        holder.itemPrice.setText(String.valueOf(cartItemList.get(position).getPrice()));

        holder.cardView.setTag(position);
        holder.quantity.setText(String.valueOf(cartItemList.get(position).getQuantity()));


    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public void update(List<CartItem> value) {
        cartItemList = value;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView quantity;
        private TextView itemName;
        private TextView itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cartItem);
            quantity = itemView.findViewById(R.id.quantityCartList);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.priceCart);
        }
    }


}
