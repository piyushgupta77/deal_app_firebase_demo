package com.example.myapp.network_library.model;

/**
 * Created by piyushgupta01 on 17-06-2019.
 */

public class SignInDto {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    String email;
    boolean isEmailVerified;
}
