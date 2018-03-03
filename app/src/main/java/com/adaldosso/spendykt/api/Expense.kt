package com.adaldosso.spendykt.api

import com.adaldosso.spendykt.utils.SpendyUtils.extractMonth
import com.adaldosso.spendykt.utils.SpendyUtils.extractYear
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Expense pojo
 *
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

class Expense(year: String) : BaseExpense(year) {

    @SerializedName("date")
    @Expose
    val date: String? = null

    override var month: String?
        get() = extractMonth(date)
        set(value) {
            super.month = value
        }

    override fun getYear(): String {
        return extractYear(date)
    }
}
