package com.example.kondadeliveryapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.kondadeliveryapp.models.CartItem;

@Database(entities = {CartItem.class}, version = 2)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
}