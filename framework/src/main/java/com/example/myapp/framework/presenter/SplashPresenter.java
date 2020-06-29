package com.example.myapp.framework.presenter;

import com.example.myapp.framework.base.BasePresenter;
import com.example.myapp.framework.domain.PasswordResetUseCase;
import com.example.myapp.framework.domain.SendVerificationEmailUseCase;
import com.example.myapp.framework.domain.SignInUserUseCase;
import com.example.myapp.framework.domain.SignOutUserUseCase;
import com.example.myapp.network_library.model.exceptions.LoginFailureException;
import com.example.myapp.network_library.network.client.Constants;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter<SplashView> {

    private final SignInUserUseCase signInUserUseCase;
    private final PasswordResetUseCase passwordResetUseCase;
    private final SendVerificationEmailUseCase sendEmailVerification;
    private final SignOutUserUseCase signOutUserUseCase;

    @Inject
    SplashPresenter(SignInUserUseCase signInUserUseCase,
                    PasswordResetUseCase passwordResetUseCase, SendVerificationEmailUseCase sendEmailVerification, SignOutUserUseCase signOutUserUseCase) {
        this.signInUserUseCase = signInUserUseCase;
        this.passwordResetUseCase = passwordResetUseCase;
        this.sendEmailVerification = sendEmailVerification;
        this.signOutUserUseCase = signOutUserUseCase;
    }

    public void handleRegisterUserClick() {
        view.navigateToRegistratinScreen();
    }

    public void handlePasswordReset(String email) {
        passwordResetUseCase.execute(email)
                .compose(bindToLifeCycle())
                .doOnSubscribe(disposable -> {
                    view.showProgress(true);
                })
                .doOnTerminate(() -> {
                    view.showProgress(false);
                })
                .subscribe(result -> {
                    if (result) {
                        view.showToast("Password reset email sent to your email address");
                    } else {
                        view.showError();
                    }
                }, throwable -> {
                    String toastMessage = "Login failure";
                    if (throwable instanceof LoginFailureException) {
                        toastMessage = toastMessage + ": No such user";
                    }
                    view.showToast(toastMessage);
                });
    }

    public void signIn(String email, String password) {
        signInUserUseCase.execute(email, password)
                .compose(bindToLifeCycle())
                .doOnSubscribe(disposable -> {
                    view.showProgress(true);
                })
                .doOnTerminate(() -> {
                    view.showProgress(false);
                })
                .subscribe(authResult -> {
                    if (authResult.isEmailVerified()) {
                        view.showToast("Login successfull");
                        if (null != authResult.getEmail()
                                && authResult.getEmail().endsWith(Constants.MERCHANT_DOMAIN_ADDRESS)) {
                            view.showMerchantScreen();
                        } else {
                            view.showDealsScreen();
                        }
                    } else {
                        view.showToast("Email not verified. Please verify your email");
                    }
                }, throwable -> {
                    view.showError();
                });
    }

    public void verifyEmail(String email, String password) {
        signInUserUseCase.execute(email, password)
                .compose(bindToLifeCycle())
                .doOnSubscribe(disposable -> {
                    view.showProgress(true);
                })
                .doOnTerminate(() -> {
                    view.showProgress(false);
                })
                .subscribe(authResult -> {
                    if (!authResult.isEmailVerified()) {
                        sendEmailVerification(email, password);
                    } else {
                        view.showToast("Email already verified");
                    }
                }, throwable -> {
                    view.showError();
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

    public void signOut() {
        signOutUserUseCase.execute(null)
                .compose(bindToLifeCycle())
                .subscribe(result -> {

                });
    }
}
