package com.phorest.shane.clientvouchers;

/**
 * Created by hp on 25/08/2017.
 */
//The outermost nesting of the json object

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageResponse {

    @SerializedName("_embedded")
    @Expose
    public Embedded embedded;

}
