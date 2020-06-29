package com.example.myapp.di.modules;

import android.app.Activity;

import dagger.Module;

@Module
public class ActivityModule {
    Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

}
