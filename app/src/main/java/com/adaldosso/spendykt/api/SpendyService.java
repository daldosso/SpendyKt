package com.adaldosso.spendykt.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

public interface SpendyService {

    @GET("users/{user}/repos")
    Call<List<Expense>> listExpenses(@Path("user") String user);

}
