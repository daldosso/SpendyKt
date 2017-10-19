package com.adaldosso.spendykt.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.adaldosso.spendykt.MainActivity;
import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.api.Expense;
import com.adaldosso.spendykt.utils.ExpensesAdapter;
import com.adaldosso.spendykt.utils.NameValuePair;
import com.adaldosso.spendykt.utils.SpendyUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

import static com.adaldosso.spendykt.utils.SpendyUtils.showMessage;

public abstract class SpendyListFragment extends Fragment implements AbsListView.OnItemClickListener, View.OnClickListener {

    private static final String CLASS_TAG = SpendyListFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ExpensesAdapter expensesAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = getActivity().findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this::fillList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        recyclerView = view.findViewById(R.id.expensesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    protected abstract int getLayout();

    protected abstract int getLayoutListItem();

    public void fillList() {
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new NameValuePair(SpendyUtils.MONTH, ""));
        params.add(new NameValuePair(SpendyUtils.YEAR, ""));
        SpendyUtils.getRows(getBaseUrl(), params, this::fillListCallback);
    }

    protected abstract String getBaseUrl();

    private Void fillListCallback(List<Expense> expenses) {
        setExpenses(expenses);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.progressBar).setVisibility(View.GONE);
        return null;
    }

    private void setExpenses(List<Expense> expenses) {
        expensesAdapter = new ExpensesAdapter(expenses, recyclerView);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setOnClickListener(this);
        recyclerView.setAdapter(expensesAdapter);
        expensesAdapter
            .getOnClickSubject()
            .subscribe(expense -> onExpenseClick(expense));
    }

    protected void onExpenseClick(Expense expense) {}

    public void addExpense(View view) {
        ((MainActivity) getActivity()).addExpense(view);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        fillList();
    }

    @Override
    public void onClick(View view) {
        showMessage(getContext(), "onClick");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        showMessage(getContext(), "onItemClick");
    }

    protected List<Expense> getExpenses() {
        return expensesAdapter.getExpenses();
    }
}
