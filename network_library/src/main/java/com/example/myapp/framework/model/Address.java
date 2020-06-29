package com.example.myapp.framework.model;

import com.google.firebase.firestore.GeoPoint;

/**
 * Created by piyushgupta01 on 25-02-2019.
 */

public class Address {
    public String line1;
    public String line2;
    public GeoPoint location;
    public int pin;
}
