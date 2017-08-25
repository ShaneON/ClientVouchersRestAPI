package com.phorest.shane.clientvouchers;

/**
 * Created by hp on 24/08/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("clientId")
    @Expose
    public String clientId;

    @SerializedName("mobile")
    @Expose
    public String mobile;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("creatingBranchId")
    @Expose
    public String creatingBranchId;


}

