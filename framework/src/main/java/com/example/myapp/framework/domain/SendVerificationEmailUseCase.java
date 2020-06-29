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

public class SendVerificationEmailUseCase extends TwoArgUseCase<String, String, Boolean> {

    private final FirebaseRepository firebaseRepository;

    @Inject
    SendVerificationEmailUseCase(UseCaseComposer useCaseComposer, FirebaseRepository firebaseRepository) {
        super(useCaseComposer);
        this.firebaseRepository = firebaseRepository;
    }


    @Override
    protected Observable<Boolean> createUseCaseObservable(String userName, String password) {
        return firebaseRepository.sendVerificationEmail(userName, password);
    }
}
