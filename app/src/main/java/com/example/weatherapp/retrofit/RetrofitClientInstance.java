package com.example.weatherapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit instance;
    private static final String BASE_URL="https://www.metaweather.com/";

    public static Retrofit getInstance() {
        if (instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
        return instance;
    }

}
