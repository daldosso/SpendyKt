package com.adaldosso.spendykt.fragments

import android.support.v7.widget.RecyclerView
import android.widget.AdapterView
import com.adaldosso.spendykt.MainActivity
import com.adaldosso.spendykt.R
import com.adaldosso.spendykt.api.BaseExpense
import com.adaldosso.spendykt.api.MonthlyExpense
import com.adaldosso.spendykt.utils.BaseExpensesAdapter
import com.adaldosso.spendykt.utils.MonthlyExpensesAdapter
import com.adaldosso.spendykt.utils.SpendyUtils.getMonthlyExpenses

class MonthlyListFragment : SpendyListFragment(), AdapterView.OnItemClickListener {

    override val layout: Int
        get() = R.layout.fragment_expenses_list

    override val layoutResourceId: Int
        get() = R.layout.monthly_expense

    override fun fillList() {
        showProgressBar()
        getMonthlyExpenses { fillListCallback(it) }
    }

    override fun hideProgressBar() {

    }

    private fun showProgressBar() {

    }

    override fun createExpensesAdapter(expenses: List<BaseExpense>, recyclerView: RecyclerView?): BaseExpensesAdapter =
        MonthlyExpensesAdapter(expenses, recyclerView, layoutResourceId)

    override fun onExpenseClick(expense: BaseExpense) {
        val monthlyExpense = expense as MonthlyExpense
        val activity = activity as MainActivity
        if (monthlyExpense.getYear() != null && monthlyExpense.month != null) {
            activity.loadExpenses(monthlyExpense.getYear()!!, monthlyExpense.month!!)
        }
    }

    override fun onResume() {
        super.onResume()
        fillList()
    }
}
