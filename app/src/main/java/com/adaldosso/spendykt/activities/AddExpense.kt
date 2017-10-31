package com.adaldosso.spendykt.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.EditText
import butterknife.BindView
import com.adaldosso.spendykt.R


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

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view -> {
//                Expense(date.toString())
            }
        }
    }

}
