package com.example.myapp.framework.domain;

import com.example.myapp.framework.base.UseCase;
import com.example.myapp.framework.base.UseCaseComposer;
import com.example.myapp.framework.data.repositories.FirebaseRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class GetCategoriesUseCase extends UseCase<String,Map<String,Object>> {

    private final FirebaseRepository firebaseRepository;

    @Inject
    GetCategoriesUseCase(UseCaseComposer useCaseComposer, FirebaseRepository firebaseRepository) {
        super(useCaseComposer);
        this.firebaseRepository = firebaseRepository;
    }


    @Override
    protected Observable<Map<String, Object>> createUseCaseObservable(String categoryName) {
        return firebaseRepository.getCollection(categoryName);
    }
}
