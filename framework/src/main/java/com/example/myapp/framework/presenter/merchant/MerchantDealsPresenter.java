package com.example.myapp.framework.presenter.merchant;

import com.example.myapp.framework.base.BasePresenter;
import com.example.myapp.framework.domain.GetMerchantDealsUseCase;
import com.example.myapp.network_library.network.client.Constants;

import javax.inject.Inject;

/**
 * Created by piyushgupta01 on 18-06-2019.
 */

public class MerchantDealsPresenter extends BasePresenter<MerchantDealsView> {
    private final GetMerchantDealsUseCase getMerchantDealsUseCase;

    @Inject
    public MerchantDealsPresenter(GetMerchantDealsUseCase getCategoriesUseCase) {
        this.getMerchantDealsUseCase = getCategoriesUseCase;
    }

    public void getDeals(String merchantId) {
        getMerchantDealsUseCase.execute(Constants.COLLECTION_CONSTANTS.DEALS, merchantId)
                .compose(bindToLifeCycle())
                .subscribe(result -> {
                    view.setDealList(result);
                }, throwable -> {
                    view.showToast("Unable to fetch data");
                });
    }
}
