package com.example.myapp.framework.domain;

import com.example.myapp.framework.base.TwoArgUseCase;
import com.example.myapp.framework.base.UseCaseComposer;
import com.example.myapp.framework.data.repositories.FirebaseRepository;
import com.example.myapp.framework.model.Deal;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class GetMerchantDealsUseCase extends TwoArgUseCase<String,String,List<Deal>> {

    private final FirebaseRepository firebaseRepository;

    @Inject
    GetMerchantDealsUseCase(UseCaseComposer useCaseComposer, FirebaseRepository firebaseRepository) {
        super(useCaseComposer);
        this.firebaseRepository = firebaseRepository;
    }


    @Override
    protected Observable<List<Deal>> createUseCaseObservable(String categoryName, String merchantId) {
        return firebaseRepository.getMerchantDeals(categoryName, merchantId);
    }
}
