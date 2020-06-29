package com.example.myapp.framework.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;

/**
 * Created by piyushgupta01 on 25-02-2019.
 */

public class Deal implements Parcelable{
    public String id;
    public String code;
    public Timestamp enddate;
    public String category;
    public String imageurl;
    public GeoPoint location;
    public String merchant_id;
    public int pin;
    public int price;
    public String summary;
    public String title;

    public Deal(){
    }

    protected Deal(Parcel in) {
        id = in.readString();
        code = in.readString();
        enddate = in.readParcelable(Timestamp.class.getClassLoader());
        category = in.readString();
        imageurl = in.readString();
        merchant_id = in.readString();
        pin = in.readInt();
        price = in.readInt();
        summary = in.readString();
        title = in.readString();
    }

    public String getMerchantId() {
        return merchant_id;
    }

    public static final Creator<Deal> CREATOR = new Creator<Deal>() {
        @Override
        public Deal createFromParcel(Parcel in) {
            return new Deal(in);
        }

        @Override
        public Deal[] newArray(int size) {
            return new Deal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(code);
        parcel.writeParcelable(enddate, i);
        parcel.writeString(category);
        parcel.writeString(imageurl);
        parcel.writeString(merchant_id);
        parcel.writeInt(pin);
        parcel.writeInt(price);
        parcel.writeString(summary);
        parcel.writeString(title);
    }
}
