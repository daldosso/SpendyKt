package com.adaldosso.spendykt.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * API interfaces
 *
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

public interface SpendyService {

    @GET("spendykt-expenses")
    Call<List<Expense>> listExpenses();

    @GET("spendykt-monthly-expenses")
    Call<List<MonthlyExpense>> listMonthlyExpenses();

    @POST("spendykt-expenses")
    Call<String> postExpense(@Body Expense body);

}
