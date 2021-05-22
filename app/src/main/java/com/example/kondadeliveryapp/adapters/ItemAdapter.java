package com.example.kondadeliveryapp.adapters;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kondadeliveryapp.MainActivity;
import com.example.kondadeliveryapp.R;
import com.example.kondadeliveryapp.models.CartItem;
import com.example.kondadeliveryapp.models.Item;
import com.example.kondadeliveryapp.viewmodels.CartViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

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
//
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());
            bottomSheetDialog.setContentView(R.layout.menu_item_add_bottom_layout);
            ConstraintLayout copy = bottomSheetDialog.findViewById(R.id.bottomL);
            assert copy != null;
            copy.shouldDelayChildPressedState();
            Button add = copy.findViewById(R.id.add_bottom);
            TextView foodName = copy.findViewById(R.id.bottom_food_name);

            int clickedPosition = (int) v.getTag();
            foodName.setText(items.get(clickedPosition).getName());
            TextView quantity = copy.findViewById(R.id.quantity);
            ImageButton incr = copy.findViewById(R.id.increment);
            ImageButton decr = copy.findViewById(R.id.decrement);
            incr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = Integer.parseInt(quantity.getText().toString());
                    i++;
                    quantity.setText(String.valueOf(i));
                }
            });
            decr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int i = Integer.parseInt(quantity.getText().toString());
                    if (i <= 1){
                        Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    i--;
                    quantity.setText(String.valueOf(i));
                }
            });




            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vt) {

                    int clickedPosition = (int) v.getTag();
                    CartItem cartItem = new CartItem(Integer.parseInt(quantity.getText().toString()), items.get(clickedPosition).getName(), items.get(clickedPosition).getPrice(), items.get(clickedPosition).getUrl());
                    MainActivity.cartViewModel.insertItem(cartItem);

                }
            });
            bottomSheetDialog.show();
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