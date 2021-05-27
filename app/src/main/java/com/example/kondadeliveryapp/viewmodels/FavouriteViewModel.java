package com.example.kondadeliveryapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.kondadeliveryapp.models.CartItem;
import com.example.kondadeliveryapp.models.FavouriteItem;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {


    private MutableLiveData<List<FavouriteItem>> data;
    private Repository repository;

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        this.repository = CartViewModel.repository;
        this.data = repository.getFavouriteList();
    }

    public MutableLiveData<List<FavouriteItem>> getFavouriteList() {
        return repository.getFavouriteList();
    }

    public void insert(FavouriteItem favouriteItem){
        repository.insertFavouriteItem(favouriteItem);
    }
}
