package com.example.myapp.framework.presenter.merchant;

import com.example.myapp.framework.base.BaseView;
import com.example.myapp.framework.model.Deal;

import java.util.List;

/**
 * Created by piyushgupta01 on 18-06-2019.
 */

public interface MerchantDealsView extends BaseView {
    void setDealList(List<Deal> result);
}
