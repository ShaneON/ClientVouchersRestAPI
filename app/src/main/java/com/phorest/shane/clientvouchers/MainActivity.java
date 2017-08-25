package com.phorest.shane.clientvouchers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private List<Client> clientList;
    private String clientId;
    private ApiService apiService;

    private EditText emailEditText;
    private EditText voucherEditText;
    private EditText phoneEditText;

    private final String password = "VMlRo/eh+Xd8M~l";
    private final String username = "global/cloud@apiexamples.com";
    private final String authHeader = "Basic " + Base64.encodeToString((username + ":" + password)
            .getBytes(), Base64.NO_WRAP);
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voucherEditText = (EditText) findViewById(R.id.voucher_edittext);
        emailEditText = (EditText) findViewById(R.id.search_edittext);
        phoneEditText = (EditText) findViewById(R.id.phone_edittext);


        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .header("Authorization", authHeader)
                        .build();
                return chain.proceed(newRequest);
            }
        };

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        builder.interceptors().add(logging);
        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api-gateway-dev.phorest.com/third-party-api-server/api/business/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        authorizationRequest();

    }

    private void authorizationRequest() {
        apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.login();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(MainActivity.this, "Authorization Successful!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Authorization unsuccessful.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSearchForClient(View view) {
        Call<PageResponse> call;
        if(!"".equals(emailEditText.getText().toString())){
            String email = emailEditText.getText().toString();
            call = apiService.getInfoByEmail(email);
        }

        else call = apiService.getInfoByPhone(phoneEditText.getText().toString());

        call.enqueue(new Callback<PageResponse>() {
            @Override
            public void onResponse(Call<PageResponse> call, Response<PageResponse> response) {
                PageResponse pageRes = response.body();
                clientList = pageRes.embedded.clients;
                clientId = clientList.get(0).clientId;
                Toast.makeText(MainActivity.this, "Found client with ID " + clientId,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PageResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "There was a failure in the connection.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        emailEditText.setText("");
        phoneEditText.setText("");
    }

    public void onCreateVoucher(View view) {
        String amountStr = voucherEditText.getText().toString();
        if(!"".equals(amountStr)){
            //Date issueDate = Calendar.getInstance().getTime();
            //Date expiryDate = Calendar.getInstance().getTime()
            //String expiryDate =
            final double amount = Double.parseDouble(voucherEditText.getText().toString());
            Voucher voucher = new Voucher(clientId, "SE-J0emUgQnya14mOGdQSw", "2018-07-11", "2017-07-11", amount);
            Call<Voucher> call = apiService.createVoucher(voucher);

            call.enqueue(new Callback<Voucher>() {
                @Override
                public void onResponse(Call<Voucher> call, Response<Voucher> response) {
                    Voucher v = response.body();
                    String displayVoucherAmount = Double.toString(v.getOriginalBalance());
                    Toast.makeText(MainActivity.this, "You created a voucher worth " + displayVoucherAmount +
                             " for client " + clientId, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<Voucher> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "There was a failure in the connection.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        else Toast.makeText(MainActivity.this, "Please enter an amount.", Toast.LENGTH_SHORT).show();

        voucherEditText.setText("");
    }
}
