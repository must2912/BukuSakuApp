package com.stematel.bukusaku.Service;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stematel.bukusaku.Network.NetworkServices;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tofa-pc on 1/24/2018.
 */

public class Service {
    private final NetworkServices apiService;
    private static Service instance = null;


    private Retrofit retrofit;

    public static Service getInstance() {
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }

    private Service() {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        retrofit= new Retrofit.Builder().baseUrl("http://212.237.62.15:8000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkHttp())
                .build();

        apiService = retrofit.create(NetworkServices.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public NetworkServices getApiService() {
        return apiService;
    }

    private OkHttpClient getOkHttp(){
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

