package com.adaldosso.spendykt.fragments;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.adaldosso.spendykt.MainActivity;
import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.api.Expense;
import com.adaldosso.spendykt.utils.SpendyUtils;

import static com.adaldosso.spendykt.utils.SpendyUtils.extractMonth;
import static com.adaldosso.spendykt.utils.SpendyUtils.extractYear;

public class ExpensesListFragment extends SpendyListFragment implements AbsListView.OnItemClickListener {

    @Override
    protected int getLayout() {
        return R.layout.fragment_expenses_list;
    }

    @Override
    protected int getLayoutListItem() {
        return R.layout.expense;
    }

    @Override
    protected String getBaseUrl() {
        return SpendyUtils.MONTHLY_OUTGOINGS_URL;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    protected void onExpenseClick(Expense expense) {
        MainActivity activity = (MainActivity) getActivity();
        String date = expense.getDate();
        activity.loadMonthlyOutgoings(extractYear(date), extractMonth(date));
    }

}
