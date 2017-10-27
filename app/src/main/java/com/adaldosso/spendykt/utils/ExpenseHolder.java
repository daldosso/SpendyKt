package com.adaldosso.spendykt.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.adaldosso.spendykt.R;

/**
 * Expenses visual holder pattern
 *
 * Created by Alberto Dal Dosso on 11/10/2017.
 */

class ExpenseHolder extends RecyclerView.ViewHolder {

    private TextView year;
    private TextView month;

    ExpenseHolder(View itemView) {
        super(itemView);
        year = itemView.findViewById(R.id.year);
        month = itemView.findViewById(R.id.month);
    }

    TextView getYear() {
        return year;
    }

    TextView getMonth() {
        return month;
    }
}
