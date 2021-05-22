package com.example.kondadeliveryapp.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.kondadeliveryapp.db.CartDao;
import com.example.kondadeliveryapp.db.CartDatabase;
import com.example.kondadeliveryapp.models.CartItem;

import java.util.LinkedList;
import java.util.List;

public class Repository {

    private Application application;
    private CartDatabase cartDatabase;
    private MutableLiveData<List<CartItem>> cartItemData;

    public Repository(Application application) {
        this.application=application;
        cartDatabase = Room.databaseBuilder(application.getApplicationContext(),
                CartDatabase.class, "cart").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        cartItemData = new MutableLiveData<>();
        loadData();
    }

    private void loadData() {

        List<CartItem> cartItems =  cartDatabase.cartDao().getAll();

            cartItemData.setValue(cartItems);



    }

    public MutableLiveData<List<CartItem>> getCartItemList() {

        return cartItemData;

    }

    public void insertCartItem(CartItem cartItem) {
        CartDao cartDao = cartDatabase.cartDao();
        List<CartItem> list = cartDao.getAll();
        cartDao.insert(cartItem);

        list.add(cartItem);
        cartItemData.setValue(list);
    }

    public void removeAll() {
        CartDao cartDao = cartDatabase.cartDao();
        cartDao.deleteAll();
        List<CartItem> l = cartDao.getAll();
        cartItemData.setValue(l);
    }
}
