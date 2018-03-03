package com.adaldosso.spendykt.utils

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.adaldosso.spendykt.R

/**
 * Expenses visual holder pattern
 *
 * Created by Alberto Dal Dosso on 11/10/2017.
 */

internal class ExpenseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val year: TextView = itemView.findViewById(R.id.year)
    val month: TextView = itemView.findViewById(R.id.month)
    val amount: TextView = itemView.findViewById(R.id.amount)
    val date: TextView? = itemView.findViewById(R.id.date)

}
