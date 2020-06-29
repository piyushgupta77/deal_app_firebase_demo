package com.example.myapp.framework.domain;

import com.example.myapp.framework.base.TwoArgUseCase;
import com.example.myapp.framework.base.UseCaseComposer;
import com.example.myapp.framework.data.repositories.FirebaseRepository;
import com.example.myapp.network_library.model.SignInDto;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class SignInUserUseCase extends TwoArgUseCase<String, String, SignInDto> {

    private final FirebaseRepository firebaseRepository;

    @Inject
    SignInUserUseCase(UseCaseComposer useCaseComposer, FirebaseRepository firebaseRepository) {
        super(useCaseComposer);
        this.firebaseRepository = firebaseRepository;
    }


    @Override
    protected Observable<SignInDto> createUseCaseObservable(String userName, String password) {
        return firebaseRepository.signIn(userName, password);
    }
}
