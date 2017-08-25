package com.phorest.shane.clientvouchers;

/**
 * Created by hp on 25/08/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditAccount {

    @SerializedName("creditDays")
    @Expose
    public Integer creditDays;
    @SerializedName("creditLimit")
    @Expose
    public Double creditLimit;

}