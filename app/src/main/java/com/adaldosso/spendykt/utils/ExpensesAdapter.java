package com.adaldosso.spendykt.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adaldosso.spendykt.api.Expense;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static com.adaldosso.spendykt.R.layout.expense;

/**
 *
 * Created by Alberto Dal Dosso on 11/10/2017.
 */

public class ExpensesAdapter extends RecyclerView.Adapter<ExpenseHolder> {

    private final List<Expense> expenses;
    private ViewGroup parent;

    private final PublishSubject<Expense> onClickSubject = PublishSubject.create();

    public ExpensesAdapter(List<Expense> expenses, ViewGroup parent) {
        this.expenses = expenses;
        this.parent = parent;
    }

    @Override
    public ExpenseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(expense, parent, false);
        return new ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.getYear().setText(expense.getYear());
        holder.getMonth().setText(expense.getMonth());
        holder.itemView.setOnClickListener(v -> onClickSubject.onNext(expense));
    }

    public Observable<Expense> getPositionClicks(){
        return onClickSubject;
    }

    @Override
    public int getItemCount() {
        if (expenses != null) {
            return expenses.size();
        }
        return 0;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public PublishSubject<Expense> getOnClickSubject() {
        return onClickSubject;
    }
}
