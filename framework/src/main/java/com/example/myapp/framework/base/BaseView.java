package com.example.myapp.framework.base;

public interface BaseView {
    void finish();

    void showProgress(boolean toShow);

    void showToast(String msg);
}
