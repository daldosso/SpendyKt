package com.adaldosso.spendykt.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adaldosso.spendykt.api.BaseExpense;
import com.adaldosso.spendykt.api.Expense;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 *
 * Created by Alberto Dal Dosso on 11/10/2017.
 */

public class ExpensesAdapter extends BaseExpensesAdapter {

    private final List<? extends Expense> expenses;
    private ViewGroup parent;

    private final PublishSubject<BaseExpense> onClickSubject = PublishSubject.create();
    private int layoutResourceId;

    public ExpensesAdapter(List<? extends BaseExpense> expenses, ViewGroup parent, int layoutResourceId) {
        super(expenses, parent, layoutResourceId);
        this.expenses = (List<? extends Expense>) expenses;
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
        Expense expense = expenses.get(position);
        holder.getYear().setText(expense.getYear());
        holder.getMonth().setText(expense.getMonth());
        holder.itemView.setOnClickListener(v -> onClickSubject.onNext(expense));
    }

    public Observable<BaseExpense> getPositionClicks(){
        return onClickSubject;
    }

    @Override
    public int getItemCount() {
        if (expenses != null) {
            return expenses.size();
        }
        return 0;
    }

    public List<? extends Expense> getExpenses() {
        return expenses;
    }

    public PublishSubject<BaseExpense> getOnClickSubject() {
        return onClickSubject;
    }
}
