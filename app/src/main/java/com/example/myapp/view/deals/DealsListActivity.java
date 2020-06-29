package com.example.myapp.view.deals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp.R;
import com.example.myapp.base.BaseActivity;
import com.example.myapp.di.AppDI;
import com.example.myapp.framework.model.Deal;
import com.example.myapp.framework.presenter.deals.DealListView;
import com.example.myapp.framework.presenter.deals.DealsListPresenter;
import com.example.myapp.network_library.network.client.Constants;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by piyushgupta01 on 24-02-2019.
 */

public class DealsListActivity extends BaseActivity implements View.OnClickListener, DealFragment.OnListFragmentInteractionListener , DealListView{
    private static final String TAG = DealsListActivity.class.getCanonicalName();
    private EditText query;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private int count = 1;

    @Inject
    DealsListPresenter dealsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);
        AppDI.getActivityComponent(this).inject(this);

        dealsListPresenter.bind(this);
        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        tablayout.setupWithViewPager(viewPager);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);

        query = findViewById(R.id.query);
        query.setText(Constants.COLLECTION_CONSTANTS.CATEGORIES);

//        getCategories();
        getAllCategories();
    }

    private void getAllCategories() {
        dealsListPresenter.getCategories();

    }

    @Override
    public void onClick(View view) {
//        addDealsData();
//        addMerchantWithAddressReference();
//        resetDealIds();
    }


    @Override
    public void onListFragmentInteraction(Deal item) {
        Intent intent = new Intent(DealsListActivity.this, DealDetailsActivity.class);
        intent.putExtra(Constants.BUNDLE_KEYS.DEAL_DETAILS, item);
        startActivity(intent);
    }

    @Override
    public void setCategories(List<String> categories) {
        for (int index = 0; index < categories.size(); index++) {
            TabLayout.Tab tab = tablayout.newTab();
            tab.setText(categories.get(index));
            tablayout.addTab(tab);
        }
        viewPager.setAdapter(new DealsListAdapter(getSupportFragmentManager(), categories));
    }
}
