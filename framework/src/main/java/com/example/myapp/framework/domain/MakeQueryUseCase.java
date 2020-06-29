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

public class MakeQueryUseCase extends TwoArgUseCase<String, String,List<Deal>> {

    private final FirebaseRepository firebaseRepository;

    @Inject
    MakeQueryUseCase(UseCaseComposer useCaseComposer, FirebaseRepository firebaseRepository) {
        super(useCaseComposer);
        this.firebaseRepository = firebaseRepository;
    }

    @Override
    protected Observable<List<Deal>> createUseCaseObservable(String qeuryCollection, String dealCategory) {
        return firebaseRepository.makeQuery(qeuryCollection, dealCategory);
    }
}
