package com.example.weatherapp.singletone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants {

    private static Constants instance;
    private static Context context;

    public Constants(Context context) {
        Constants.context = context;
    }


    //create singleton class

    public static Constants getInstance(Context context) {
        if(instance == null){

            instance = new Constants(context);

        }
        return instance;
    }
    //for internet access if available in device.id can work either on wifi or mobile data.
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm= (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        if(networkInfo!=null){
            if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI)
                return true;
            return networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;

    }

    public class City {

        public static final String LONDON = "44418/";
        public static final String INDIA = "2427032/";
        public static final String SAN_FRAN = "2487956/";
        public static final String DUBAI = "1940345/";
        public static final String MOSCOW = "2122265/";
        public static final String MONTREAL = "3534/";
    }

    public class WeatherStateAbbr {
        public static final String CLEAR = "c";
        public static final String HAIL = "h";
        public static final String HEAVY_CLOUD = "hc";
        public static final String HEAVY_RAIN = "hr";
        public static final String LIGHT_CLOUD = "lc";
        public static final String LIGHT_RAIN = "lr";
        public static final String SHOWERS = "s";
        public static final String SLEET = "sl";
        public static final String SNOW = "sn";
        public static final String THUNDERSTORM = "t";

    }

}
