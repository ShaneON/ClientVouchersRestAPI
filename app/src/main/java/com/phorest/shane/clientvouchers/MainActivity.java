package com.phorest.shane.clientvouchers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    private List<Client> clientList;
    private String clientId;
    private String TAG = "MainActivity";
    private ApiService apiService;
    private TextView searchTextView;
    private Button searchButton;
    private EditText searchEditText;
    private final String password = "VMlRo/eh+Xd8M~l";
    private final String username = "global/cloud@apiexamples.com";
    //private final String password = "passwd";
    //private final String username = "user";
    private final String authHeader = "Basic " + Base64.encodeToString((username + ":" + password)
            .getBytes(), Base64.NO_WRAP);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchTextView = (TextView) findViewById(R.id.search_textview);
        searchEditText = (EditText) findViewById(R.id.search_edittext);
        searchButton = (Button) findViewById(R.id.search_button);

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api-gateway-dev.phorest.com/third-party-api-server/api/business/")
                //.baseUrl("https://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.login();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void onSearchForClient(View view) {
        Log.d(TAG, "============================================  I am inside onsearchforclient");

        Call<PageResponse> call = apiService.getInfo("kevin.walsh@phorest.com");
        call.enqueue(new Callback<PageResponse>() {
            @Override
            public void onResponse(Call<PageResponse> call, Response<PageResponse> response) {
                Log.d(TAG, "=======================================================I am inside onresponse");
                PageResponse pageRes = response.body();
                clientList = pageRes.embedded.clients;
                searchEditText.setText(clientList.get(0).email);
                clientId = clientList.get(0).clientId;
                Log.d(TAG, "==============================================================" + clientId);
                /*int count = 0;
                for(int i = 0; i < clientList.size(); i++){
                    if(clientList.get(i).creatingBranchId.equals("eTC3QY5W3p_HmGHezKfxJw"))
                        count++;
                }
                Log.d(TAG, "NUMBER OF BRANCH IDS = " + count);*/
            }

            @Override
            public void onFailure(Call<PageResponse> call, Throwable t) {
                Log.d(TAG, "=======================================================I am inside onfailure");
            }
        });
        /*
        Log.d(TAG, "============================================  I am inside onsearchforclient");
        Call<HTTPBin> call = apiService.getInfo();
        call.enqueue(new Callback<HTTPBin>() {
            @Override
            public void onResponse(Call<HTTPBin> call, Response<HTTPBin> response) {
                Log.d(TAG, "=======================================================I am inside onresponse");
                HTTPBin clients = response.body();
                searchEditText.setText(clients.user);
            }

            @Override
            public void onFailure(Call<HTTPBin> call, Throwable t) {
                Log.d(TAG, "=======================================================I am inside onfailure");
            }
        });
        */
    }
}
