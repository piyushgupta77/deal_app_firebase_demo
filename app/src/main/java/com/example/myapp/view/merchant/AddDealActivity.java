package com.example.myapp.view.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapp.R;
import com.example.myapp.base.BaseActivity;
import com.example.myapp.di.AppDI;
import com.example.myapp.framework.model.Deal;
import com.example.myapp.framework.presenter.deals.AddDealPresenter;
import com.example.myapp.framework.presenter.deals.AddDealView;
import com.example.myapp.network_library.network.client.Constants;

import javax.inject.Inject;

public class AddDealActivity extends BaseActivity implements View.OnClickListener , AddDealView{

    private AppCompatEditText etCategory;
    private AppCompatEditText etCode;
    private AppCompatEditText etPrice;
    private AppCompatEditText etSummary;
    private AppCompatEditText etTitle;
    private AppCompatEditText etMerchantId;
    private ProgressBar progressBar;

    @Inject
    AddDealPresenter addDealPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deal);
        AppDI.getActivityComponent(this).inject(this);
        addDealPresenter.bind(this);

        initToolbar(findViewById(R.id.toolbar), true, true);

        progressBar = findViewById(R.id.progressbar);
        etCategory = findViewById(R.id.category);
        etCode = findViewById(R.id.code);
        etPrice = findViewById(R.id.price);
        etSummary = findViewById(R.id.summary);
        etTitle = findViewById(R.id.title);
        etMerchantId = findViewById(R.id.merchant_id);

        findViewById(R.id.submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Deal deal = new Deal();
        deal.category = etCategory.getText().toString();
        deal.code = etCode.getText().toString();
        deal.price = Integer.valueOf(etPrice.getText().toString());
        deal.title = etTitle.getText().toString();
        deal.summary = etSummary.getText().toString();
        deal.imageurl = Constants.RANDOM_IMAGE_URL;
        deal.merchant_id = etMerchantId.getText().toString();

        addDealPresenter.addDeal(deal);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void showMerchantScreen(String merchant_id) {
        Intent intent = new Intent(AddDealActivity.this, MerchantDealsActivity.class);
        intent.putExtra(Constants.BUNDLE_KEYS.MERCHANT_ID, merchant_id);
        startActivity(intent);
        finish();
    }
}
