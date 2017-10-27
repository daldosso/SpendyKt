package com.adaldosso.spendykt.api;

/**
 * Expense pojo
 *
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

public class MonthlyExpense extends BaseExpense {
    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }
}
