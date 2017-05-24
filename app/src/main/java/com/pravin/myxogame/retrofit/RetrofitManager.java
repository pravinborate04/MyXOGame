package com.pravin.myxogame.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by webwerks on 16/3/16.
 */
public class RetrofitManager {

    private static RetrofitManager mRetrofitManager;
    private static final String BASE_URL = "https://fcm.googleapis.com/fcm/";

    private static Retrofit mRetrofitClient;

    private RetrofitManager() {
    }


    public static RetrofitManager getInstance() {
        if(mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {

                mRetrofitManager = new RetrofitManager();
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);


                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(logging);

                mRetrofitClient = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return mRetrofitManager;
    }

    public Retrofit getClient() {
        return mRetrofitClient;
    }
}


