package com.adaldosso.spendykt.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.api.BaseExpense;
import com.adaldosso.spendykt.utils.BaseExpensesAdapter;

import java.util.List;

import static com.adaldosso.spendykt.utils.SpendyUtils.showMessage;

public abstract class SpendyListFragment extends Fragment implements AbsListView.OnItemClickListener, View.OnClickListener {

    protected RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = getActivity().findViewById(R.id.swiperefresh);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(this::fillList);
        }
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
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    protected abstract int getLayout();

    public abstract void fillList();

    protected Void fillListCallback(List<? extends BaseExpense> expenses) {
        setExpenses(expenses);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
//        getActivity().findViewById(R.id.progressBar).setVisibility(View.GONE);
        return null;
    }

    private void setExpenses(List<? extends BaseExpense> expenses) {
        BaseExpensesAdapter expensesAdapter = createExpensesAdapter(expenses, recyclerView);
/*
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
*/
        if (recyclerView != null) {
            recyclerView.setOnClickListener(this);
            recyclerView.setAdapter(expensesAdapter);
        }
        expensesAdapter
            .getOnClickSubject()
            .subscribe(this::onExpenseClick);
    }

    protected abstract BaseExpensesAdapter createExpensesAdapter(List<? extends BaseExpense> expenses, RecyclerView recyclerView);

    protected abstract int getLayoutResourceId();

    protected void onExpenseClick(BaseExpense expense) {}

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

}
