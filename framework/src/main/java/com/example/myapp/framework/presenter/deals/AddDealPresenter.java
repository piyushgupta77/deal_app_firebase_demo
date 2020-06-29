package com.example.myapp.framework.presenter.deals;

import com.example.myapp.framework.base.BasePresenter;
import com.example.myapp.framework.domain.AddDealUseCase;
import com.example.myapp.framework.model.Deal;
import com.example.myapp.network_library.network.client.Constants;

import javax.inject.Inject;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class AddDealPresenter extends BasePresenter<AddDealView> {

    private final AddDealUseCase addDealUseCase;

    @Inject
    public AddDealPresenter(AddDealUseCase addDealUseCase) {
        this.addDealUseCase = addDealUseCase;
    }

    public void addDeal(Deal deal) {
        addDealUseCase.execute(Constants.COLLECTION_CONSTANTS.DEALS, deal)
                .compose(bindToLifeCycle())
                .doOnSubscribe(disposable -> {
                    view.showProgress(true);
                })
                .doOnTerminate(() -> {
                    view.showProgress(false);
                })
                .subscribe(result -> {
                    view.showToast("Deal added: " + result);
                    view.showMerchantScreen(deal.getMerchantId());
                    view.finish();
                }, throwable -> {
                    view.showToast("Unable to add deal, please try again later");
                });
    }
}
