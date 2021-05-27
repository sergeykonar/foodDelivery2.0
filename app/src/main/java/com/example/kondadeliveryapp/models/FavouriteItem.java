package com.example.kondadeliveryapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class FavouriteItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String address;

    private String image;

    private float delivery_charge;






    public FavouriteItem(String name, String address, String image, float delivery_charge) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.delivery_charge = delivery_charge;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(float delivery_charge) {
        this.delivery_charge = delivery_charge;
    }




}
