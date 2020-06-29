package com.example.myapp.framework.presenter.deals;

import com.example.myapp.framework.base.BasePresenter;
import com.example.myapp.framework.domain.GetCategoriesUseCase;
import com.example.myapp.network_library.network.client.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class DealsListPresenter extends BasePresenter<DealListView> {

    GetCategoriesUseCase getCategoriesUseCase;

    @Inject
    public DealsListPresenter(GetCategoriesUseCase getCategoriesUseCase){
        this.getCategoriesUseCase = getCategoriesUseCase;
    }

    public void getCategories() {
        getCategoriesUseCase.execute(Constants.COLLECTION_CONSTANTS.CATEGORIES)
                .compose(bindToLifeCycle())
                .subscribe(result -> {
                    List<String> categories = (ArrayList<String>) result.get(Constants.COLLECTION_CONSTANTS.CATEGORIES);
                    view.setCategories(categories);
                }, throwable -> {

                });
    }
}
