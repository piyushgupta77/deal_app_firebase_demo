package com.example.myapp.di.components;

import com.example.myapp.di.modules.ActivityModule;
import com.example.myapp.scopes.ActivityScope;
import com.example.myapp.view.deals.DealDetailsActivity;
import com.example.myapp.view.deals.DealsListActivity;
import com.example.myapp.view.merchant.AddDealActivity;
import com.example.myapp.view.merchant.MerchantDealsActivity;
import com.example.myapp.view.register.RegisterActivity;
import com.example.myapp.view.splash.SplashActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity mainActivity);
    void inject(RegisterActivity mainActivity);
    void inject(DealsListActivity mainActivity);
    void inject(DealDetailsActivity mainActivity);
    void inject(AddDealActivity mainActivity);
    void inject(MerchantDealsActivity mainActivity);
}
