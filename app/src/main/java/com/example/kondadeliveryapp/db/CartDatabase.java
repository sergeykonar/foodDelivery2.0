package com.example.kondadeliveryapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.kondadeliveryapp.models.CartItem;
import com.example.kondadeliveryapp.models.FavouriteItem;

@Database(entities = {CartItem.class, FavouriteItem.class}, version = 3)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao cartDao();

    public abstract FavouriteDao favouriteDao();
}