package com.example.kondadeliveryapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Menu implements Parcelable {
    public String category_name;
    public List<Item> items;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    protected Menu(Parcel in) {
        category_name = in.readString();


    }

    public static final Parcelable.Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public String getName() {
        return category_name;
    }

    public void setName(String name) {
        this.category_name = name;
    }





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category_name);
        dest.writeList(items);

    }
}