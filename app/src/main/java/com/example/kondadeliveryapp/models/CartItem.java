package com.example.kondadeliveryapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.LinkedList;

@Entity
public class CartItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int quantity;

    public String name;

    public double price;

    public String url;

    public CartItem(int quantity, String name, double price, String url) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
