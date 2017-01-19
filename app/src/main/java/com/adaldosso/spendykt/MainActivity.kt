package com.adaldosso.spendykt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.AbsListView
import com.adaldosso.spendykt.fragments.ExpensesListFragment
import com.adaldosso.spendykt.utils.ExpenseAdapter
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addDrawer()
        //populateList()
        loadExpensesListFragment()
    }

    private fun loadExpensesListFragment() {
        val transaction = fragmentManager.beginTransaction()
        val expensesListFragment = ExpensesListFragment()
        expensesListFragment.fillList()
//        expensesListFragment.setJsonArray(getLastExpenses())
        transaction.replace(R.id.activity_main, expensesListFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun populateList() {
        var listView = findViewById(R.id.expenses_list) as AbsListView
        var expenseAdapter = ExpenseAdapter(this, getLastExpenses())
        listView.setAdapter(expenseAdapter)
    }

    private fun getLastExpenses(): JSONArray {
        return JSONArray()
/*
        return JSONArray("[" +
            "{'date': '01/01/2017', 'amount': 150.43}" +
            "{'date': '04/01/2017', 'amount': 50.00}" +
            "{'date': '06/01/2017', 'amount': 10.37}" +
        "]")
*/
    }

    private fun addDrawer() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(R.string.app_name)

        val item1 = PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home)
        val item2 = SecondaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_settings)

        val result = DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        DividerDrawerItem(),
                        item2,
                        SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                )
                .withOnDrawerItemClickListener { view, position, drawerItem -> true }
                .build()
    }

    fun loadMonthlyOutgoings(year: Int, month: Int) {
/*
        val params = ArrayList<NameValuePair>(2)
        params.add(BasicNameValuePair(Utils.MONTH, month.toString()))
        params.add(BasicNameValuePair(Utils.YEAR, year.toString()))
        val rows = getRows(Utils.MONTHLY_OUTGOINGS_URL, params)
*/
        val transaction = fragmentManager.beginTransaction()
//        val monthlyOutgoingFragment = MonthlyOutgoingFragment()
//        monthlyOutgoingFragment.setJsonArray(rows)
//        transaction.replace(R.id.activity_main, monthlyOutgoingFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
