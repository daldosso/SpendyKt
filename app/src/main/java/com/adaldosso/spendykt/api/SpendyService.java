package com.adaldosso.spendykt.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * API interfaces
 *
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

public interface SpendyService {

    @GET("spendykt-expenses")
    Call<List<Expense>> listExpenses();

}
