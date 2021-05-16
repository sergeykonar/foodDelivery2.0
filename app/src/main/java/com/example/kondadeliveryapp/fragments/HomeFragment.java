package com.example.kondadeliveryapp.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kondadeliveryapp.R;
import com.example.kondadeliveryapp.adapters.RestaurantsAdapter;
import com.example.kondadeliveryapp.models.Restaurant;
import com.example.kondadeliveryapp.viewmodels.BlankViewModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private BlankViewModel mViewModel;
    private RecyclerView recyclerView;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BlankViewModel.class);
        List<Restaurant> restaurants = getRestaurantData();
        RestaurantsAdapter adapter = new RestaurantsAdapter(restaurants, getContext());

        initRecyclerView();

        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView(){
        recyclerView = requireView().findViewById(R.id.restaurantList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private List<Restaurant> getRestaurantData() {
        InputStream is = getResources().openRawResource(R.raw.restaurant);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try{
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while(( n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0,n);
            }
        }catch (Exception e) {

        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        Restaurant[] restaurantModels =  gson.fromJson(jsonStr, Restaurant[].class);
        List<Restaurant> restList = Arrays.asList(restaurantModels);

        return  restList;

    }

}