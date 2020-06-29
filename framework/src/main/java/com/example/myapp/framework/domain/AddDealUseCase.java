package com.example.myapp.framework.domain;

import com.example.myapp.framework.base.TwoArgUseCase;
import com.example.myapp.framework.base.UseCaseComposer;
import com.example.myapp.framework.data.repositories.FirebaseRepository;
import com.example.myapp.framework.model.Deal;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class AddDealUseCase extends TwoArgUseCase<String, Deal, String> {

    private final FirebaseRepository mFirebaseRepository;

    @Inject
    AddDealUseCase(UseCaseComposer useCaseComposer, FirebaseRepository firebaseRepository) {
        super(useCaseComposer);
        this.mFirebaseRepository = firebaseRepository;
    }


    @Override
    protected Observable<String> createUseCaseObservable(String collection, Deal dealToBeAdded) {
        return mFirebaseRepository.addDeal(collection, dealToBeAdded);
    }
}
