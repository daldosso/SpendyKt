package com.adaldosso.spendykt.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adaldosso.spendykt.api.BaseExpense;
import com.adaldosso.spendykt.api.MonthlyExpense;

import java.util.List;

/**
 *
 * Created by Alberto Dal Dosso on 11/10/2017.
 */

public class MonthlyExpensesAdapter extends BaseExpensesAdapter {

    private final List<MonthlyExpense> expenses;
    private ViewGroup parent;
    private int layoutResourceId;

    public MonthlyExpensesAdapter(List<? extends BaseExpense> expenses, ViewGroup parent, int layoutResourceId) {
        super(expenses, parent, layoutResourceId);
        this.expenses = (List<MonthlyExpense>) expenses;
        this.parent = parent;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public ExpenseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
        return new ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseHolder holder, int position) {
        MonthlyExpense monthlyExpense = expenses.get(position);
        holder.getYear().setText(monthlyExpense.getYear());
        holder.getMonth().setText(monthlyExpense.getMonth());
        holder.getAmount().setText(String.valueOf(monthlyExpense.getAmount()));
        holder.itemView.setOnClickListener(v -> onClickSubject.onNext(monthlyExpense));
    }

    @Override
    public int getItemCount() {
        if (expenses != null) {
            return expenses.size();
        }
        return 0;
    }

}
