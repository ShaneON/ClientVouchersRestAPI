package com.phorest.shane.clientvouchers;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hp on 24/08/2017.
 */

public interface ApiService {

    //Get client by their email, which is a query parameter
    @GET("eTC3QY5W3p_HmGHezKfxJw/client")
    Call<PageResponse> getInfoByEmail(@Query("email") String email);

    //Get the client by phone number which is a query parameter
    @GET("eTC3QY5W3p_HmGHezKfxJw/client")
    Call<PageResponse> getInfoByPhone(@Query("mobile") String mobile);

    //get request for authorization
    @GET("eTC3QY5W3p_HmGHezKfxJw/client")
    Call<ResponseBody> login();

    //Post voucher object to api
    @POST("eTC3QY5W3p_HmGHezKfxJw/voucher")
    Call<Voucher> createVoucher(@Body Voucher voucher);
}
