package com.example.myapp.framework.presenter.deals;

import com.example.myapp.framework.domain.AddDealUseCase;
import com.example.myapp.framework.model.Deal;
import com.example.myapp.network_library.network.client.Constants;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

/**
 * Created by piyushgupta01 on 27-07-2019.
 */

public class AddDealPresenterTest {
    @InjectMocks
    AddDealPresenter mAddDealPresenter;

    @Mock
    AddDealUseCase mAddDealUseCase;

    @Mock
    AddDealView mAddDealView;

    @Mock
    Deal mDeal;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mAddDealPresenter.bind(mAddDealView);
    }

    @Test
    public void testDealAddition_Success() {
        String addedDealGeneratedId = "ahd2133";
        String merchantId = "123";
        Mockito.when(mDeal.getMerchantId()).thenReturn(merchantId);

        Mockito.when(mAddDealUseCase.execute(Constants.COLLECTION_CONSTANTS.DEALS, mDeal))
                .thenReturn(Observable.just(addedDealGeneratedId));

        mAddDealPresenter.addDeal(mDeal);

        Mockito.verify(mAddDealView).showToast("Deal added: " + addedDealGeneratedId);
        Mockito.verify(mAddDealView).showMerchantScreen(merchantId);
        Mockito.verify(mAddDealView).finish();
    }

    @Test
    public void testDealAddition_Failure() {
        String merchantId = "123";
        Mockito.when(mDeal.getMerchantId()).thenReturn(merchantId);
        Mockito.when(mAddDealUseCase.execute(Constants.COLLECTION_CONSTANTS.DEALS, mDeal))
                .thenReturn(Observable.error(new Throwable()));

        mAddDealPresenter.addDeal(mDeal);

        Mockito.verify(mAddDealView).showToast("Unable to add deal, please try again later");
    }
}
