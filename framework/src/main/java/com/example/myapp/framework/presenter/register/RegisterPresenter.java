package com.example.myapp.framework.presenter.register;

import com.example.myapp.framework.base.BasePresenter;
import com.example.myapp.framework.domain.CreateUserNameUseCase;
import com.example.myapp.framework.domain.SendVerificationEmailUseCase;
import com.example.myapp.network_library.model.exceptions.UserAlreadyExistException;

import javax.inject.Inject;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {

    private final CreateUserNameUseCase mCreateUserNameUseCase;
    private final SendVerificationEmailUseCase sendEmailVerification;

    @Inject
    RegisterPresenter(CreateUserNameUseCase createUserNameUseCase, SendVerificationEmailUseCase sendEmailVerification) {
        this.mCreateUserNameUseCase = createUserNameUseCase;
        this.sendEmailVerification = sendEmailVerification;
    }

    public void handleCreateUserNameClick(String userEmail, String password) {
        //TODO verify email and password
        mCreateUserNameUseCase.execute(userEmail, password)
                .compose(bindToLifeCycle())
                .doOnSubscribe(disposable -> {
                    view.showProgress(true);
                })
                .doOnTerminate(() -> {
                    view.showProgress(false);
                })
                .subscribe(authResult -> {
                    sendEmailVerification(userEmail, password);
                    view.showToast("Registration successful. Please verify your email to login.");
                    view.finish();
                }, throwable -> {
                    if (throwable instanceof UserAlreadyExistException) {
                        view.showToast("User already registered");
                    } else {
                        view.showToast("User registration failed");
                    }
                });
    }

    private void sendEmailVerification(String email, String password) {
        sendEmailVerification.execute(email, password)
                .compose(bindToLifeCycle())
                .subscribe(result -> {
                    if (result) {
                        view.showToast("Password reset email sent to your email address");
                    }
                }, throwable -> {
                    view.showError();
                });
    }

}
