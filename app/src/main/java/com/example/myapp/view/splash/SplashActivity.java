package com.example.myapp.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myapp.R;
import com.example.myapp.base.BaseActivity;
import com.example.myapp.di.AppDI;
import com.example.myapp.framework.presenter.SplashPresenter;
import com.example.myapp.framework.presenter.SplashView;
import com.example.myapp.network_library.network.client.Constants;
import com.example.myapp.view.deals.DealsListActivity;
import com.example.myapp.view.merchant.AddDealActivity;
import com.example.myapp.view.merchant.MerchantDealsActivity;
import com.example.myapp.view.register.RegisterActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView, View.OnClickListener {

    private static final String TAG = SplashActivity.class.getCanonicalName();
    private EditText email;
    private EditText password;

    @Inject
    SplashPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDI.getActivityComponent(this).inject(this);

        setOnClickListener(findViewById(R.id.txt_forgot_password));
        setOnClickListener(findViewById(R.id.txt_verify_email));
        setOnClickListener(findViewById(R.id.submit));
        setOnClickListener(findViewById(R.id.register));

        progressBar = findViewById(R.id.progressbar);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        if(Constants.IS_TESTING){
            email.setText(Constants.TESTING_DATA.userEmail);
            password.setText(Constants.TESTING_DATA.password);
        }
    }

    private void setOnClickListener(View view) {
        view.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.bind(this);
        //sign out when user comes back to this screen
        mMainPresenter.signOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMainPresenter.unbind();
    }

    @Override
    public void showError() {
        Log.d(TAG, "failure");
    }

    @Override
    public void navigateToRegistratinScreen() {
        Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMerchantScreen() {
        Intent intent = new Intent(SplashActivity.this, MerchantDealsActivity.class);
        if (Constants.IS_TESTING) {
            intent.putExtra(Constants.BUNDLE_KEYS.MERCHANT_ID, "M3");
        } else {
            intent = new Intent(SplashActivity.this, AddDealActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void showDealsScreen() {
        startActivity(new Intent(SplashActivity.this, DealsListActivity.class));

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register) {
            mMainPresenter.handleRegisterUserClick();
        } else if (view.getId() == R.id.txt_forgot_password) {
            if (null != email && !TextUtils.isEmpty(email.getText())) {
                mMainPresenter.handlePasswordReset(email.getText().toString());
            }
        } else if (view.getId() == R.id.submit) {
            if (null != email && !TextUtils.isEmpty(email.getText())
                    && null != password && !TextUtils.isEmpty(password.getText())) {
                mMainPresenter.signIn(email.getText().toString(), password.getText().toString());
            }
        } else if (view.getId() == R.id.txt_verify_email) {
            if (null != email && !TextUtils.isEmpty(email.getText())
                    && null != password && !TextUtils.isEmpty(password.getText())) {
                mMainPresenter.verifyEmail(email.getText().toString(), password.getText().toString());
            }
        }
    }

}
