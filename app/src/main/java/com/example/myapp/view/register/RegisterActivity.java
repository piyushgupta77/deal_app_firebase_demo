package com.example.myapp.view.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp.R;
import com.example.myapp.base.BaseActivity;
import com.example.myapp.di.AppDI;
import com.example.myapp.framework.presenter.register.RegisterPresenter;
import com.example.myapp.framework.presenter.register.RegisterView;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterView {

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private EditText email;
    private EditText password;

    @Inject
    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AppDI.getActivityComponent(this).inject(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressbar);

        Button btnSubmit = findViewById(R.id.submit);
        btnSubmit.setOnClickListener(this);

        registerPresenter.bind(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit) {
            registerPresenter.handleCreateUserNameClick(email.getText().toString(), password.getText().toString());
        }
    }

    @Override
    public void showError() {

    }
}
