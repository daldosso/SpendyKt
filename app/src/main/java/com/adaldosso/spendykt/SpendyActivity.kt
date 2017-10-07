package com.adaldosso.spendykt

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

open class SpendyActivity : AppCompatActivity() {

    fun showToast(message: String) {
        val context = applicationContext
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, message, duration)
        toast.show()
    }

}
