package com.example.myapp.framework.domain;

import com.example.myapp.framework.base.UseCase;
import com.example.myapp.framework.base.UseCaseComposer;
import com.example.myapp.framework.data.repositories.FirebaseRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class PasswordResetUseCase extends UseCase<String,Boolean> {

    private final FirebaseRepository firebaseRepository;

    @Inject
    PasswordResetUseCase(UseCaseComposer useCaseComposer, FirebaseRepository firebaseRepository) {
        super(useCaseComposer);
        this.firebaseRepository = firebaseRepository;
    }


    @Override
    protected Observable<Boolean> createUseCaseObservable(String userName) {
        return firebaseRepository.resetPassword(userName);
    }
}
