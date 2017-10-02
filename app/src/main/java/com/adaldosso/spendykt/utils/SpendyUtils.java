package com.adaldosso.spendykt.utils;

import android.util.Log;

import com.adaldosso.spendykt.api.Expense;
import com.adaldosso.spendykt.api.SpendyService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

public class SpendyUtils {
    public static final String MONTH = "mese";
    public static final String YEAR = "anno";
    public static final String MONTHLY_OUTGOINGS_URL = "http://www.adaldosso.com/quantospendi/srv/totali-categorie.php";
    private static final String CLASS_TAG = SpendyUtils.class.getSimpleName();

    public static List<Expense> getRows(@NotNull String url, @NotNull ArrayList<NameValuePair> params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpendyService service = retrofit.create(SpendyService.class);
        Call<List<Expense>> expenses = service.listExpenses("octocat");
        try {
            Response<List<Expense>> response = expenses.execute();
            return response.body();
        } catch (IOException e) {
            Log.e(CLASS_TAG, e.getMessage());
        }
        return null;
    }
}
