package com.adaldosso.spendykt.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.adaldosso.spendykt.api.Expense;
import com.adaldosso.spendykt.api.MonthlyExpense;
import com.adaldosso.spendykt.api.SpendyResponse;
import com.adaldosso.spendykt.api.SpendyService;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Utils class
 *
 * Created by Alberto Dal Dosso on 02/10/2017.
 */

public class SpendyUtils {
    public static final String MONTH = "mese";
    public static final String YEAR = "anno";
    private static final String CLASS_TAG = SpendyUtils.class.getSimpleName();
    private static final String BASE_URL = "https://spendynode.herokuapp.com/";

    public static void getRows(@NotNull List<NameValuePair> params, Function<List<Expense>, Void> onResponse) {
        SpendyService service = getRetrofit().create(SpendyService.class);
        Call<List<Expense>> expenses = service.listExpenses();
        expenses.enqueue(new Callback<List<Expense>>() {
            @Override
            public void onResponse(@NonNull Call<List<Expense>> call, @NonNull Response<List<Expense>> response) {
                onResponse.apply(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Expense>> call, @NonNull Throwable t) {
                Log.e(CLASS_TAG, t.getMessage());
            }
        });
    }

    public static void getMonthlyExpenses(Function<List<MonthlyExpense>, Void> onResponse) {
        SpendyService service = getRetrofit().create(SpendyService.class);
        Call<List<MonthlyExpense>> monthlyExpenses = service.listMonthlyExpenses();
        monthlyExpenses.enqueue(new Callback<List<MonthlyExpense>>() {
            @Override
            public void onResponse(@NonNull Call<List<MonthlyExpense>> call, @NonNull Response<List<MonthlyExpense>> response) {
                onResponse.apply(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<MonthlyExpense>> call, @NonNull Throwable t) {
                Log.e(CLASS_TAG, t.getMessage());
            }
        });
    }

    public static void postExpense(@NotNull Expense expense, Function<SpendyResponse, Void> onResponse) {
        SpendyService service = getRetrofit().create(SpendyService.class);
        Call<SpendyResponse> expenses = service.postExpense(expense);
        expenses.enqueue(new Callback<SpendyResponse>() {

            @Override
            public void onResponse(Call<SpendyResponse> call, Response<SpendyResponse> response) {
                Log.i(CLASS_TAG, response.message());
            }

            @Override
            public void onFailure(Call<SpendyResponse> call, Throwable t) {
                Log.e(CLASS_TAG, t.getMessage());
            }
        });
    }

    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }

    private static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private static DateTime convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        return formatter.parseDateTime(date);
    }

    public static String extractMonth(String date) {
        DateTime dateTime = convertDate(date);
        YearMonth yearMonth = new YearMonth(dateTime.getYear(), dateTime.getMonthOfYear());
        return SpendyUtils.capitalize(yearMonth.monthOfYear().getAsText(new Locale("it"))) ;
    }

    public static String extractYear(String date) {
        return String.valueOf(convertDate(date).getYear());
    }


    public static void showMessage(Context context, String message) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("Spendy")
            .setMessage(message)
            .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                // continue with delete
            })
            .setNegativeButton(android.R.string.no, (dialog, which) -> {
                // do nothing
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();
    }
}
