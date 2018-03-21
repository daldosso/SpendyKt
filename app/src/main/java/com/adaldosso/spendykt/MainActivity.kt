package com.adaldosso.spendykt

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.adaldosso.spendykt.activities.AddExpense
import com.adaldosso.spendykt.fragments.ExpensesListFragment
import com.adaldosso.spendykt.fragments.MonthlyListFragment
import com.adaldosso.spendykt.fragments.OnListFragmentInteractionListener
import com.adaldosso.spendykt.fragments.dummy.DummyContent

class MainActivity : SpendyActivity(), OnListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadStartList()
    }

    fun loadStartList() {
        val monthlyListFragment = MonthlyListFragment()
        monthlyListFragment.fillList()
        fragmentManager
            .beginTransaction()
            .add(R.id.expenses_list_container, monthlyListFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem) {

    }

    fun loadExpenses(year: String, month: String) {
/*
        val params = ArrayList<NameValuePair>(2)
        params.add(NameValuePair(SpendyUtils.MONTH, month))
        params.add(NameValuePair(SpendyUtils.YEAR, year))
        val expensesListFragment = ExpensesListFragment()
        expensesListFragment.fillList()
*/
        val expensesListFragment = ExpensesListFragment()
//        val expensesListFragment = ItemFragment()
//        expensesListFragment.fillList()

        fragmentManager
            .beginTransaction()
            //.setCustomAnimations(R.animator.slide_left_in, R.animator.slide_left_out, R.animator.slide_right_in, R.animator.slide_right_out)
            .replace(R.id.expenses_list_container, expensesListFragment, "findThisFragment")
            .addToBackStack(null)
            .commit()
    }

    fun addExpense(view: View) {
        startActivity(Intent(this, AddExpense::class.java))
    }

}
