package com.adaldosso.spendykt.fragments;

import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

import com.adaldosso.spendykt.MainActivity;
import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.api.BaseExpense;
import com.adaldosso.spendykt.api.MonthlyExpense;
import com.adaldosso.spendykt.utils.BaseExpensesAdapter;
import com.adaldosso.spendykt.utils.MonthlyExpensesAdapter;
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
    protected BaseExpensesAdapter createExpensesAdapter(List<? extends BaseExpense> expenses, RecyclerView recyclerView) {
        return new MonthlyExpensesAdapter(expenses, recyclerView, getLayoutResourceId());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.monthly_expense;
    }

    @Override
    protected void onExpenseClick(BaseExpense baseExpense) {
        MonthlyExpense monthlyExpense = (MonthlyExpense) baseExpense;
        MainActivity activity = (MainActivity) getActivity();
        activity.loadExpenses(monthlyExpense.getYear(), monthlyExpense.getMonth());
    }

}
