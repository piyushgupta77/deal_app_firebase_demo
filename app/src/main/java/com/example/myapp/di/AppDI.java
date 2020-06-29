package com.example.myapp.di;

import android.app.Fragment;

import com.example.myapp.base.BaseActivity;
import com.example.myapp.base.BaseFragment;
import com.example.myapp.di.components.ActivityComponent;
import com.example.myapp.di.components.ApplicationComponent;
import com.example.myapp.di.components.DaggerApplicationComponent;
import com.example.myapp.di.components.FragmentComponent;
import com.example.myapp.di.modules.ActivityModule;
import com.example.myapp.di.modules.ApplicationModule;
import com.example.myapp.di.modules.FragmentModule;

public class AppDI {
    private static ApplicationComponent applicationComponent;

    private static ApplicationComponent getApplicationComponent(BaseActivity activity) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(activity.getApplication()))
                .build();
        return applicationComponent;
    }

    private static ApplicationComponent getApplicationComponent(BaseFragment fragment) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(fragment.getActivity().getApplication()))
                .build();
        return applicationComponent;
    }

    public static ActivityComponent getActivityComponent(BaseActivity activity) {
        return getApplicationComponent(activity).plus(new ActivityModule(activity));
    }

    public static FragmentComponent getFragmentComponent(BaseFragment fragment) {
        return getApplicationComponent(fragment).plus(new FragmentModule(fragment));
    }

}
