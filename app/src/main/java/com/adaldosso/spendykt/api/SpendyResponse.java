package com.adaldosso.spendykt.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Standard JSON response
 *
 * Created by Alberto Dal Dosso on 04/11/2017.
 */

public class SpendyResponse {

    @SerializedName("success")
    @Expose
    private String success;

    public boolean isSuccess() {
        return success.equalsIgnoreCase("true");
    }
}
