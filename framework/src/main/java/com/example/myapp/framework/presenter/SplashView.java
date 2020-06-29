package com.example.myapp.framework.presenter;

import com.example.myapp.framework.base.BaseView;

public interface SplashView extends BaseView{
    void showError();

    void navigateToRegistratinScreen();

    void showMerchantScreen();

    void showDealsScreen();
}
