package com.adaldosso.spendykt.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.adaldosso.spendykt.utils.SpendyUtils.extractMonth;
import static com.adaldosso.spendykt.utils.SpendyUtils.extractYear;

/**
 * Expense pojo
 *
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

public class Expense extends BaseExpense {

    @SerializedName("date")
    @Expose
    private String date;

    public Expense(String year) {
        super(year);
    }

    public String getYear() {
        return extractYear(date);
    }

    public String getMonth() {
        return extractMonth(date);
    }

    public String getDate() {
        return date;
    }
}
