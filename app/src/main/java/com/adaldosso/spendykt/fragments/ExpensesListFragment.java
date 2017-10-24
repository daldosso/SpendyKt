package com.adaldosso.spendykt.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.adaldosso.spendykt.MainActivity;
import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.api.BaseExpense;
import com.adaldosso.spendykt.api.Expense;
import com.adaldosso.spendykt.utils.ExpensesAdapter;
import com.adaldosso.spendykt.utils.NameValuePair;
import com.adaldosso.spendykt.utils.SpendyUtils;

import java.util.ArrayList;
import java.util.List;

import static com.adaldosso.spendykt.utils.SpendyUtils.extractMonth;
import static com.adaldosso.spendykt.utils.SpendyUtils.extractYear;

public class ExpensesListFragment extends SpendyListFragment implements AbsListView.OnItemClickListener {

    @Override
    protected int getLayout() {
        return R.layout.fragment_expenses_list;
    }

    @Override
    public void fillList() {
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new NameValuePair(SpendyUtils.MONTH, ""));
        params.add(new NameValuePair(SpendyUtils.YEAR, ""));
        SpendyUtils.getRows(params, this::fillListCallback);
    }

    @Override
    protected ExpensesAdapter createExpensesAdapter(List<? extends BaseExpense> expenses, RecyclerView recyclerView) {
        return new ExpensesAdapter((List<? extends Expense>) expenses, recyclerView, getLayoutResourceId());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.expense;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    protected void onExpenseClick(Expense expense) {
        MainActivity activity = (MainActivity) getActivity();
        String date = expense.getDate();
        activity.loadExpenses(extractYear(date), extractMonth(date));
    }

}
