package com.example.myapp.view.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapp.R;
import com.example.myapp.base.BaseActivity;
import com.example.myapp.di.AppDI;
import com.example.myapp.framework.model.Deal;
import com.example.myapp.framework.presenter.merchant.MerchantDealsPresenter;
import com.example.myapp.framework.presenter.merchant.MerchantDealsView;
import com.example.myapp.network_library.network.client.Constants;
import com.example.myapp.view.deals.DealFragment;
import com.example.myapp.view.deals.MyDealRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by piyushgupta01 on 27-02-2019.
 */

public class MerchantDealsActivity extends BaseActivity implements DealFragment.OnListFragmentInteractionListener, MerchantDealsView {
    private static final String TAG = MerchantDealsActivity.class.getCanonicalName();
    private RecyclerView recyclerView;

    @Inject
    MerchantDealsPresenter merchantDealsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_deal_list);

        AppDI.getActivityComponent(this).inject(this);
        merchantDealsPresenter.bind(this);

        initToolbar(findViewById(R.id.toolbar), true, true);
        recyclerView = findViewById(R.id.list);

        if (getIntent() != null && getIntent().hasExtra(Constants.BUNDLE_KEYS.MERCHANT_ID)) {
            String merchantId = getIntent().getStringExtra(Constants.BUNDLE_KEYS.MERCHANT_ID);
            if (!TextUtils.isEmpty(merchantId)) {
                merchantDealsPresenter.getDeals(merchantId);
            }
        }
    }

    @Override
    public void onListFragmentInteraction(Deal item) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deal_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_add_deal) {
            Intent intent = new Intent(MerchantDealsActivity.this, AddDealActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void setDealList(List<Deal> result) {
        recyclerView.setAdapter(new MyDealRecyclerViewAdapter(result, MerchantDealsActivity.this));
    }
}
