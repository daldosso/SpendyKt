package com.adaldosso.spendykt.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.api.BaseExpense;
import com.adaldosso.spendykt.utils.ExpensesAdapter;
import com.adaldosso.spendykt.utils.NameValuePair;
import com.adaldosso.spendykt.utils.SpendyUtils;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class ExpensesListFragment extends SpendyListFragment implements AbsListView.OnItemClickListener {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) getActivity().findViewById(R.id.textView)).setText("ExpensesListFragment");
        getActivity().findViewById(R.id.textView).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.expensesList).setVisibility(GONE);
        getActivity().findViewById(R.id.expensesList).setVisibility(GONE);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_expenses_list;
    }

    @Override
    public void fillList() {
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new NameValuePair(SpendyUtils.MONTH, ""));
        params.add(new NameValuePair(SpendyUtils.YEAR, ""));
//        SpendyUtils.getRows(params, this::fillListCallback);
        SpendyUtils.getMonthlyExpenses(this::fillListCallback);
    }

    @Override
    protected ExpensesAdapter createExpensesAdapter(List<? extends BaseExpense> expenses, RecyclerView recyclerView) {
        return new ExpensesAdapter(expenses, recyclerView, getLayoutResourceId());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.expense;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

}
