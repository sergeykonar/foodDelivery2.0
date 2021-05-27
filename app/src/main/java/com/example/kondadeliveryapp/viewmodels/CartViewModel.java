package com.example.kondadeliveryapp.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.kondadeliveryapp.models.CartItem;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private MutableLiveData<List<CartItem>> cartItemList;
    protected static Repository repository;

    public CartViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        cartItemList = repository.getCartItemList();
        Log.e("TAG", "CartViewModel ccreated");
    }

    public MutableLiveData<List<CartItem>> getCartItemList() {
        return repository.getCartItemList();
    }

    public void insertItem(CartItem cartItem){
        repository.insertCartItem(cartItem);
    }

    public void removeAll() {
        repository.removeAll();
    }
}