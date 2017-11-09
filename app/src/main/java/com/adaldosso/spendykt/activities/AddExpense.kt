package com.adaldosso.spendykt.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.adaldosso.spendykt.R
import com.adaldosso.spendykt.api.Expense
import com.adaldosso.spendykt.api.SpendyResponse
import com.adaldosso.spendykt.utils.SpendyUtils.postExpense
import com.adaldosso.spendykt.utils.SpendyUtils.showMessage

class AddExpense : AppCompatActivity() {

    @BindView(R.id.editTextDate)
    private var date: EditText? = null

    @BindView(R.id.editTextAmount)
    var amount: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        ButterKnife.bind(this)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val button = findViewById(R.id.fab) as FloatingActionButton
        date = findViewById(R.id.editTextDate) as EditText
        button.setOnClickListener {
            view -> postExpense(Expense(date!!.text.toString())) { callBack(it) }
        }
    }

    private fun callBack(response: SpendyResponse): Void? {
        showMessage(applicationContext, response.isSuccess.toString())
        return null
    }

}
