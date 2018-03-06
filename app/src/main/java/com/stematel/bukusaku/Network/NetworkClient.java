package com.stematel.bukusaku.Network;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tofa-pc on 2/3/2018.
 */

public class NetworkClient {
    private final NetworkServices networkServices;
    private final String Base_URL = "http://192.168.43.150/ApiBusak/";
    private static NetworkClient instance = null;

    private Retrofit retrofit;

    public static NetworkClient getInstance() {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }


    public NetworkClient() {

        retrofit = new Retrofit.Builder().baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttp())
                .build();

        networkServices = retrofit.create(NetworkServices.class);

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public NetworkServices getApiService() {
        return networkServices;
    }


    private OkHttpClient getOkHttp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request newRequest = request.newBuilder()
                                .addHeader("Accept", "application/json")
                                .build();

                        return chain.proceed(newRequest);
                    }
                }).build();
    }
}
