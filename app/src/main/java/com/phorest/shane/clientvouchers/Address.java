package com.phorest.shane.clientvouchers;

/**
 * Created by hp on 25/08/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("streetAddress1")
    @Expose
    public String streetAddress1;
    @SerializedName("streetAddress2")
    @Expose
    public String streetAddress2;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("postalCode")
    @Expose
    public String postalCode;
    @SerializedName("country")
    @Expose
    public String country;

}
