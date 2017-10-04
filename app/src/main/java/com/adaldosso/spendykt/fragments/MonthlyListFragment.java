package com.adaldosso.spendykt.fragments;

import android.widget.AbsListView;

import com.adaldosso.spendykt.utils.SpendyUtils;

public class MonthlyListFragment extends SpendyListFragment implements AbsListView.OnItemClickListener {

    @Override
    protected String getBaseUrl() {
        return SpendyUtils.MONTHLY_OUTGOINGS_URL;
    }
}
