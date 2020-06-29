package com.example.myapp.framework.presenter.deals;

import com.example.myapp.framework.base.BaseView;
import com.example.myapp.framework.model.Deal;

import java.util.List;

public interface DealView extends BaseView{
    void setDeals(List<Deal> dealList);

    void showError();
}
