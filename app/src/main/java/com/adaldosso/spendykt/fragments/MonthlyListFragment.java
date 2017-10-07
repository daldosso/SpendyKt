package com.adaldosso.spendykt.fragments;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.utils.SpendyUtils;

public class MonthlyListFragment extends SpendyListFragment implements AbsListView.OnItemClickListener {

    @Override
    protected int getLayout() {
        return R.layout.fragment_expenses_list;
    }

    @Override
    protected int getLayoutListItem() {
        return R.layout.monthly_expense;
    }

    @Override
    protected String getBaseUrl() {
        return SpendyUtils.MONTHLY_OUTGOINGS_URL;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
