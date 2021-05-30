package com.example.kondadeliveryapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.kondadeliveryapp.viewmodels.CartViewModel;
import com.example.kondadeliveryapp.viewmodels.FavouriteViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static CartViewModel cartViewModel;
    public static FavouriteViewModel favouriteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.historyFragment, R.id.accountFragment, R.id.favouriteFragment, R.id.cartFragment).build();
        NavController navController = Navigation.findNavController(this, R.id.fragment2);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        favouriteViewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);

    }


}