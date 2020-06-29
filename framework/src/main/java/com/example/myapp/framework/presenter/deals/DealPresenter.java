package com.example.myapp.framework.presenter.deals;

import com.example.myapp.framework.base.BasePresenter;
import com.example.myapp.framework.domain.MakeQueryUseCase;
import com.example.myapp.network_library.network.client.Constants;

import javax.inject.Inject;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class DealPresenter extends BasePresenter<DealView> {
    private MakeQueryUseCase makeQueryUseCase;

    @Inject
    public DealPresenter(MakeQueryUseCase makeQueryUseCase) {
        this.makeQueryUseCase = makeQueryUseCase;
    }

    public void getDeals(String dealCategory) {
        makeQueryUseCase.execute(Constants.COLLECTION_CONSTANTS.DEALS, dealCategory)
                .compose(bindToLifeCycle())
                .subscribe(result -> {
                    view.setDeals(result);
                }, throwable -> {
                    view.showError();
                });
    }
}
