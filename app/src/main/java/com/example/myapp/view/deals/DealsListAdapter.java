package com.example.myapp.view.deals;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by piyushgupta01 on 25-02-2019.
 */

public class DealsListAdapter extends FragmentStatePagerAdapter {
    private final List<String> categories;

    public DealsListAdapter(FragmentManager fm, List<String> categories) {
        super(fm);
        this.categories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return DealFragment.newInstance(categories.get(position));
    }

    @Override
    public int getCount() {
        return categories != null ? categories.size() : 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position);
    }
}
