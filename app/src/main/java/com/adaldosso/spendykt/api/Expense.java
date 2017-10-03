package com.adaldosso.spendykt.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

public class Expense {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("amount")
    @Expose
    private Float amount;
}
