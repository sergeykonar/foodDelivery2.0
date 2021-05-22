package com.example.kondadeliveryapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kondadeliveryapp.models.CartItem;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cartitem")
    List<CartItem> getAll();

    @Insert
    void insert(CartItem cartItem);

    @Delete
    void delete(CartItem cartItem);

    @Query("DELETE FROM cartItem")
    void deleteAll();

}
