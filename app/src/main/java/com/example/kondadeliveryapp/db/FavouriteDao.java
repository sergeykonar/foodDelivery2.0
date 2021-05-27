package com.example.kondadeliveryapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kondadeliveryapp.models.CartItem;
import com.example.kondadeliveryapp.models.FavouriteItem;

import java.util.List;

@Dao
public interface FavouriteDao {

    @Query("SELECT * FROM favouriteitem")
    List<FavouriteItem> getAll();

    @Insert
    void insert(FavouriteItem favouriteitem);

    @Delete
    void delete(FavouriteItem favouriteitem);

    @Query("DELETE FROM favouriteitem")
    void deleteAll();

}
