package com.adaldosso.spendykt.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.EditText
import butterknife.BindView
import com.adaldosso.spendykt.R
import com.adaldosso.spendykt.api.Expense
import com.adaldosso.spendykt.api.SpendyResponse
import com.adaldosso.spendykt.utils.SpendyUtils.postExpense


class AddExpense : AppCompatActivity() {

    @BindView(R.id.editTextDate)
    var date: EditText? = null

    @BindView(R.id.editTextAmount)
    var amount: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val button = findViewById(R.id.fab) as FloatingActionButton
        button.setOnClickListener { view -> postExpense(Expense(date.toString())) { callBack(it) } }
    }

    fun callBack(reponse: SpendyResponse): Void? {
        return null
    }

}
