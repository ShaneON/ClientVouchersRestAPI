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

    @GET("eTC3QY5W3p_HmGHezKfxJw/client")
    Call<PageResponse> getInfo(@Query("email") String email);

    @GET("eTC3QY5W3p_HmGHezKfxJw/client")
    Call<ResponseBody> login();

    @POST("eTC3QY5W3p_HmGHezKfxJw/voucher")
    Call<Voucher> createVoucher(@Body Voucher voucher);
}
