package com.adaldosso.spendykt.fragments

import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ProgressBar
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
        activity?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = GONE
    }

    private fun showProgressBar() {
        activity?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = VISIBLE
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
