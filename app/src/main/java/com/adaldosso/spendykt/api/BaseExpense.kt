package com.adaldosso.spendykt.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Expense pojo
 *
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

open class BaseExpense {

    @SerializedName("month")
    @Expose
    open var month: String? = null

    @SerializedName("amount")
    @Expose
    var amount: Float? = null

    @SerializedName("year")
    @Expose
    open var y: String? = null

    constructor(year: String) {
        this.y = year
    }

    open fun getYear(): String? {
        return y
    }
}