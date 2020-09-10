package com.tifone.demo.android.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Create by Tifone on 2020/9/10.
 */
public class Book implements Parcelable {

    private String id;
    private String name;
    private int price;

    public Book(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private Book(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(price);
    }


    public int getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Id: " + id + ", name: " + name + ", price: " + price;
    }
}
