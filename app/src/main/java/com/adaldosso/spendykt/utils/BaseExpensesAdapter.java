package com.adaldosso.spendykt.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adaldosso.spendykt.api.BaseExpense;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 *
 * Created by Alberto Dal Dosso on 11/10/2017.
 */

public class BaseExpensesAdapter extends RecyclerView.Adapter<ExpenseHolder> {

    private final List<BaseExpense> expenses;
    private ViewGroup parent;
    protected final PublishSubject<BaseExpense> onClickSubject = PublishSubject.create();
    private int layoutResourceId;

    public BaseExpensesAdapter(List<? extends BaseExpense> expenses, ViewGroup parent, int layoutResourceId) {
        this.expenses = (List<BaseExpense>) expenses;
        this.parent = parent;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public ExpenseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
        return new ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseHolder holder, int position) { }

    @Override
    public int getItemCount() {
        if (expenses != null) {
            return expenses.size();
        }
        return 0;
    }

    public PublishSubject<BaseExpense> getOnClickSubject() {
        return onClickSubject;
    }
}
