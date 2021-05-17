package com.example.kondadeliveryapp.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kondadeliveryapp.models.CartItem;
import com.example.kondadeliveryapp.models.Item;

import java.util.LinkedList;
import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private MutableLiveData<List<CartItem>> cartItemList;
    private Repository repository;

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