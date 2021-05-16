package com.example.kondadeliveryapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.kondadeliveryapp.fragments.AccountFragment;
import com.example.kondadeliveryapp.fragments.CartFragment;
import com.example.kondadeliveryapp.fragments.HistoryFragment;
import com.example.kondadeliveryapp.fragments.HomeFragment;
import com.example.kondadeliveryapp.fragments.ItemFragment;
import com.example.kondadeliveryapp.models.Menu;
import com.example.kondadeliveryapp.models.Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.kondadeliveryapp.ui.main.SectionsPagerAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    private List<Menu> menuList ;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        id = bundle.getInt("id");

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.view_pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);

        NUM_PAGES = getRestaurantData().get(id).getMenus().size();
        menuList = getRestaurantData().get(id).getMenus();
        pagerAdapter.notifyDataSetChanged();
//        Log.e("qw",getRestaurantData().get(id).getMenus().size());
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

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            fragment = new ItemFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("id", getRestaurantData().get(id).getMenus().get(position));

            fragment.setArguments(bundle);
            return fragment;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            menuList = getRestaurantData().get(id).getMenus();
            return menuList.get(position).getName();
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}

