package com.example.kondadeliveryapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kondadeliveryapp.CheckoutActivityJava;
import com.example.kondadeliveryapp.MainActivity;
import com.example.kondadeliveryapp.R;
import com.example.kondadeliveryapp.adapters.CartItemsAdapter;
import com.example.kondadeliveryapp.models.CartItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    private CartItemsAdapter cartItemsAdapter;
    private RecyclerView recyclerView;
    private List<CartItem> items;
    public CartFragment() {
        // Required empty public constructor
    }


    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.cartList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TextView textView = view.findViewById(R.id.empty);
        textView.setVisibility(View.INVISIBLE);
        Button clearBtn = view.findViewById(R.id.clear);
        clearBtn.setOnClickListener(l);
        MainActivity.cartViewModel.getCartItemList().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {


                cartItemsAdapter = new CartItemsAdapter(cartItems);
                recyclerView.setAdapter(cartItemsAdapter);
                cartItemsAdapter.notifyDataSetChanged();

                if (cartItems.size() == 0){
                    textView.setVisibility(View.VISIBLE);
                }

                DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), LinearLayout.VERTICAL);
                recyclerView.addItemDecoration(itemDecor);
            }
        });

        Button pay = view.findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent check = new Intent(getContext(), CheckoutActivityJava.class);
                startActivity(check);
            }
        });




        return view;
    }

    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.cartViewModel.removeAll();
            cartItemsAdapter.update(MainActivity.cartViewModel.getCartItemList().getValue());
        }
    };
}