
package com.example.myapp.network_library.model;

import com.squareup.moshi.Json;

public class CloudsEntity {

    @Json(name = "all")
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

}
