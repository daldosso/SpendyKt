package com.adaldosso.spendykt

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.adaldosso.spendykt.activities.AddExpense
import com.adaldosso.spendykt.fragments.ExpensesListFragment
import com.adaldosso.spendykt.fragments.MonthlyListFragment
import com.adaldosso.spendykt.utils.NameValuePair
import com.adaldosso.spendykt.utils.SpendyUtils
import java.util.*

class MainActivity : SpendyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadStartList()
    }

    private fun loadStartList() {
        val expensesListFragment = ExpensesListFragment()
        expensesListFragment.fillList()
        fragmentManager
            .beginTransaction()
            .replace(R.id.expenses_list_container, expensesListFragment)
            .addToBackStack(null)
            .commit()
    }

    fun loadMonthlyOutgoings(year: String, month: String) {
        val params = ArrayList<NameValuePair>(2)
        params.add(NameValuePair(SpendyUtils.MONTH, month))
        params.add(NameValuePair(SpendyUtils.YEAR, year))
        val monthlyOutgoingFragment = MonthlyListFragment()
        monthlyOutgoingFragment.fillList()
        fragmentManager
            .beginTransaction()
            .setCustomAnimations(R.animator.slide_left_in, R.animator.slide_left_out, R.animator.slide_left_in, R.animator.slide_right_out)
            .replace(R.id.expenses_list_container, monthlyOutgoingFragment)
            .addToBackStack(null)
            .commit()
    }

    fun addExpense(view: View) {
        startActivity(Intent(this, AddExpense::class.java))
    }

}
