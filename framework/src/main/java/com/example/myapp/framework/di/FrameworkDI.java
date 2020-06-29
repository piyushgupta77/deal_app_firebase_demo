package com.example.myapp.framework.di;

import com.example.myapp.framework.di.components.DaggerFrameworkApplicationComponent;
import com.example.myapp.framework.di.components.FrameworkApplicationComponent;
import com.example.myapp.framework.di.modules.FrameworkApplicationModule;

public class FrameworkDI {

    public static FrameworkApplicationComponent getApplicationComponent() {
        FrameworkApplicationComponent applicationComponent = DaggerFrameworkApplicationComponent.builder()
                .frameworkApplicationModule(new FrameworkApplicationModule())
                .build();
        return applicationComponent;
    }

}
