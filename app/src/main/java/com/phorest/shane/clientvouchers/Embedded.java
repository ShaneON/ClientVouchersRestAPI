package com.phorest.shane.clientvouchers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hp on 25/08/2017.
 */

public class Embedded {

    @SerializedName("clients")
    @Expose
    public List<Client> clients = null;

}
