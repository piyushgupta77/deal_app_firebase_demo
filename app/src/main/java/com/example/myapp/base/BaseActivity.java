package com.example.myapp.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapp.framework.base.BaseView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity implements BaseView{

    protected ProgressBar progressBar;
    protected void initToolbar(final Toolbar toolbar, final boolean showNavigationButton, boolean showTitle) {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(showNavigationButton);
            getSupportActionBar().setHomeButtonEnabled(showNavigationButton);
            getSupportActionBar().setDisplayShowTitleEnabled(showTitle);
        }
    }

    @Override
    public void showProgress(boolean showProgress) {
        if (null != progressBar) {
            progressBar.setVisibility(showProgress ? View.VISIBLE : View.GONE);
        }
        if (showProgress) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
