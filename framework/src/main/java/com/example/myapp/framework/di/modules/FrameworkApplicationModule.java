package com.example.myapp.framework.di.modules;

import android.app.Application;

import com.example.myapp.framework.base.AndroidSchedulerUseCaseComposer;
import com.example.myapp.framework.base.UseCaseComposer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FrameworkApplicationModule {

    public FrameworkApplicationModule() {
    }

    @Provides
    public UseCaseComposer provideUseCaseComposer(){
        return new AndroidSchedulerUseCaseComposer();
    }
}
