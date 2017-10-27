package com.adaldosso.spendykt.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Expense pojo
 *
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

public class BaseExpense {

    @SerializedName("year")
    @Expose
    public String year;

    @SerializedName("month")
    @Expose
    public String month;

    @SerializedName("amount")
    @Expose
    public Float amount;

}
