package com.example.myapp.view.deals;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.base.BaseActivity;
import com.example.myapp.di.AppDI;
import com.example.myapp.framework.model.Deal;
import com.example.myapp.network_library.network.client.Constants;
import com.squareup.picasso.Picasso;

public class DealDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_details);
        AppDI.getActivityComponent(this).inject(this);

        if (null != getIntent() && getIntent().hasExtra(Constants.BUNDLE_KEYS.DEAL_DETAILS)) {
            Parcelable parcelable = getIntent().getParcelableExtra(Constants.BUNDLE_KEYS.DEAL_DETAILS);
            if (parcelable instanceof Deal) {
                Deal deal = (Deal) parcelable;
                ImageView dealImage = findViewById(R.id.deal_img);
                Picasso.get().load(deal.imageurl).into(dealImage);
                TextView title = findViewById(R.id.title);
                TextView code = findViewById(R.id.code);
                TextView price = findViewById(R.id.price);
                TextView summary = findViewById(R.id.summary);
                TextView id = findViewById(R.id.id);

                title.setText(deal.title);
                code.setText(deal.code);
                price.setText(String.format(getString(R.string.price), deal.price));
                summary.setText(deal.summary);
                id.setText(deal.id);
            }
        }
    }

}
