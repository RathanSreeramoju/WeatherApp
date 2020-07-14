package com.example.weatherapp.retrofit;

import com.example.weatherapp.modelpojoclass.Example;


import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    public static final String LONDON = "44418/";
    public static final String INDIANAPOLIS = "2427032/";
    public static final String SAN_FRAN = "2487956/";
    public static final String VANCOUVER = "9807/";
    public static final String TORONTO = "4118/";
    public static final String MONTREAL = "3534/";

    @GET("api/location/3534/")
    Call<Example> getWeatherDetails();

    @GET("api/location/4118/")
    Call<Example> getTorontoWeatherDetails();

    @GET("api/location/9807/")
    Call<Example> getVancouverWeatherDetails();

    @GET("api/location/2487956/")
    Call<Example> getSanFranWeatherDetails();

    @GET("api/location/2427032/")
    Call<Example> getIndianapolisWeatherDetails();

    @GET("api/location/44418/")
    Call<Example> getLondonWeatherDetails();
}
