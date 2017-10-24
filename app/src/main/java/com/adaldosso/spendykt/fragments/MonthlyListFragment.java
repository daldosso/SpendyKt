package com.adaldosso.spendykt.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.api.BaseExpense;
import com.adaldosso.spendykt.api.Expense;
import com.adaldosso.spendykt.utils.ExpensesAdapter;
import com.adaldosso.spendykt.utils.SpendyUtils;

import java.util.List;

public class MonthlyListFragment extends SpendyListFragment implements AbsListView.OnItemClickListener {

    @Override
    protected int getLayout() {
        return R.layout.fragment_expenses_list;
    }

    @Override
    public void fillList() {
        SpendyUtils.getMonthlyExpenses(this::fillListCallback);
    }

    @Override
    protected ExpensesAdapter createExpensesAdapter(List<? extends BaseExpense> expenses, RecyclerView recyclerView) {
        return new ExpensesAdapter((List<? extends Expense>) expenses, recyclerView, getLayoutResourceId());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.monthly_expense;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
