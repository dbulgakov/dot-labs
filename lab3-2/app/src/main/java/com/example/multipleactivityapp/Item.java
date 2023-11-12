package com.example.multipleactivityapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    String itemName = "";
    String itemClassification = "";
    String itemSize = "";

    public Item(){
    }

    protected Item(Parcel in) {
        itemName = in.readString();
        itemClassification = in.readString();
        itemSize = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }
        @Override

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(itemClassification);
        dest.writeString(itemSize);
    }
}
