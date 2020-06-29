package com.example.myapp.framework.model;

/**
 * Created by piyushgupta01 on 25-02-2019.
 */

public enum Categories {
    FOOD("food"),
    TRAVEL("travel"),
    MOVIES("movies"),
    FASHION("fashion"),
    ELECTRONICS("electronics"),
    LEISURE("leisure"),
    FITNESS("fitness");

    private final String description;

    Categories(String desc) {
        description = desc;
    }

    public String getValue() {
        return this.description;
    }

}
