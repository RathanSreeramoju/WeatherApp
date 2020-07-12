package com.example.weatherapp.retrofit;

import com.example.weatherapp.modelpojoclass.Example;


import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    public static final String LONDON = "44418/";
    public static final String INDIA = "2427032/";
    public static final String SAN_FRAN = "2487956/";
    public static final String DUBAI = "1940345/";
    public static final String MOSCOW = "2122265/";
    public static final String MONTREAL = "3534/";

    @GET("api/location/3534/")
    Call<Example> getWeatherDetails();

    @GET("api/location/2122265/")
    Call<Example> getMoskowWeatherDetails();

    @GET("api/location/1940345/")
    Call<Example> getDubaiWeatherDetails();

    @GET("api/location/2487956/")
    Call<Example> getSanFranWeatherDetails();

    @GET("api/location/2427032/")
    Call<Example> getIndiaWeatherDetails();

    @GET("api/location/44418/")
    Call<Example> getLondonWeatherDetails();
}
