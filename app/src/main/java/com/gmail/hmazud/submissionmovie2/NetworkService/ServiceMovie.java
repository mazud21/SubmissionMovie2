package com.gmail.hmazud.submissionmovie2.NetworkService;

import com.gmail.hmazud.submissionmovie2.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceMovie {
    private static Retrofit retrofit;
    private static final String BASE_URL = BuildConfig.BASE_URL;

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
