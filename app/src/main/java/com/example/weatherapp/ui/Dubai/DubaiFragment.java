package com.example.weatherapp.ui.Dubai;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.FragmentMontrealBinding;
import com.example.weatherapp.modelpojoclass.ConsolidatedWeather;
import com.example.weatherapp.modelpojoclass.Example;
import com.example.weatherapp.modelpojoclass.WeatherInfo;
import com.example.weatherapp.retrofit.GetDataService;
import com.example.weatherapp.retrofit.RetrofitClientInstance;
import com.example.weatherapp.singletone.Constants;
import com.example.weatherapp.singletone.ProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DubaiFragment extends Fragment {
    private Context context;
    private FragmentMontrealBinding montrealBinding;



    public DubaiFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Default Fragment :", "OnCreateView Called!");
        // Inflate the layout for this fragment
        montrealBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_montreal,container,false);
//        view = inflater.inflate(R.layout.fragment_montreal, container, false);
        setRetrofit();
        return montrealBinding.getRoot();
    }

    private void setRetrofit() {
        if (Constants.isNetworkAvailable(context)) {
            ProgressDialog.progressDialog.show();
            GetDataService service = RetrofitClientInstance.getInstance().create(GetDataService.class);

            Call<Example> call = service.getDubaiWeatherDetails();
            System.out.println("call__" + call);
            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    ProgressDialog.progressDialog.dismiss();

                    if (response.isSuccessful()) {
                        System.out.println("Response From URL :" + response.body().toString());
                        Example list = response.body();

                        List<ConsolidatedWeather> conso = list.getConsolidatedWeather();
                        System.out.println("humidity)___"+conso.get(0).getHumidity());
                        WeatherInfo info=null;
                        List<WeatherInfo> listdata = new ArrayList<WeatherInfo>();

                        for (int i=0;i<conso.size();i++){
                            info = new WeatherInfo();
                            info.setWeatherStateName(conso.get(i).getWeatherStateName());//cityname
                            info.setMaxTemp(""+conso.get(i).getMaxTemp());
                            info.setMinTemp(""+conso.get(i).getMinTemp());
                            info.setActTemp(""+conso.get(i).getTheTemp());
                            info.setHumidity(""+conso.get(i).getHumidity());
                            info.setPredictability(""+conso.get(i).getPredictability());
                            info.setWeatherStateAbbr(conso.get(i).getWeatherStateAbbr());
                            info.setDate(conso.get(i).getApplicableDate());
                            info.setCityName(list.getTitle());
                            listdata.add(info);

                        }

                        refreshUi(listdata);

                    } else {
                        Toast.makeText(context, "failure response", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<Example> call, Throwable t) {

                    System.out.println("In Failure :" + t.getMessage());
                    ProgressDialog.progressDialog.dismiss();

                }
            });
        } else {
            Toast.makeText(context, "Please check your Internet connection.", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshUi(List<WeatherInfo> list) {
//        montrealBinding.cityName.setText(list.get(0).getCityName());
//        montrealBinding.valMinTemp.setText(String.format("%.2f", Float.valueOf(list.get(0).getMinTemp())));
        montrealBinding.valMaxTemp.setText(String.format("%.2f", Float.valueOf(list.get(0).getMaxTemp()))+"-"+String.format("%.2f", Float.valueOf(list.get(0).getMinTemp())));
        montrealBinding.valActualTemp.setText(String.format("%.2f", Float.valueOf(list.get(0).getActTemp())));
//        montrealBinding.humidity.setText(String.format(getString(R.string.humidity), list.get(0).getHumidity())+"%");
//        montrealBinding.predictability.setText(String.format(getString(R.string.predictability), list.get(0).getPredictability())+"%");
//        montrealBinding.ivIcon1.setImageResource(getImageDrawable(list.get(0).getWeatherStateAbbr()));
        montrealBinding.ivIcon2.setImageResource(getImageDrawable(list.get(1).getWeatherStateAbbr()));
        montrealBinding.ivIcon3.setImageResource(getImageDrawable(list.get(2).getWeatherStateAbbr()));
        montrealBinding.ivIcon4.setImageResource(getImageDrawable(list.get(3).getWeatherStateAbbr()));
        montrealBinding.ivIcon5.setImageResource(getImageDrawable(list.get(4).getWeatherStateAbbr()));
        montrealBinding.ivIcon6.setImageResource(getImageDrawable(list.get(5).getWeatherStateAbbr()));
        montrealBinding.tvDesc1.setText(String.format(getString(R.string.test), list.get(1).getWeatherStateName()));
        montrealBinding.tvDesc2.setText(String.format(getString(R.string.test), list.get(2).getWeatherStateName()));
        montrealBinding.tvDesc3.setText(String.format(getString(R.string.test), list.get(3).getWeatherStateName()));
        montrealBinding.tvDesc4.setText(String.format(getString(R.string.test), list.get(4).getWeatherStateName()));
        montrealBinding.tvDesc5.setText(String.format(getString(R.string.test), list.get(5).getWeatherStateName() ));
        montrealBinding.day1.setText(getDayString(list.get(1).getDate()));
        montrealBinding.day2.setText(getDayString(list.get(2).getDate()));
        montrealBinding.day3.setText(getDayString(list.get(3).getDate()));
        montrealBinding.day4.setText(getDayString(list.get(4).getDate()));
        montrealBinding.day5.setText(getDayString(list.get(5).getDate()));

        montrealBinding.middle.setText(getDayString1(list.get(5).getDate()));

        montrealBinding.last.setText(getDayString2(""+list.get(5).getDate()));

    }

    private String getDayString2(String date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date1 = simpleDateFormat.parse(date);
            calendar.setTimeInMillis(date1.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG_FORMAT, Locale.getDefault());

    }

    private String getDayString1(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date1 = simpleDateFormat.parse(date);
            calendar.setTimeInMillis(date1.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ""+calendar.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.getDefault());
    }


    private String getDayString(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date1 = simpleDateFormat.parse(date);
            calendar.setTimeInMillis(date1.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT_FORMAT, Locale.getDefault());
    }

    private int getImageDrawable(String abbr) {
        int resId;
        switch (abbr) {
            case Constants.WeatherStateAbbr.CLEAR:
                resId = R.drawable.ic_clear;
                break;
            case Constants.WeatherStateAbbr.HAIL:
                resId = R.drawable.ic_hail;
                break;
            case Constants.WeatherStateAbbr.HEAVY_CLOUD:
                resId = R.drawable.ic_heavycloud;
                break;
            case Constants.WeatherStateAbbr.HEAVY_RAIN:
                resId = R.drawable.ic_heavyrain;
                break;
            case Constants.WeatherStateAbbr.LIGHT_CLOUD:
                resId = R.drawable.ic_lightcloud;
                break;
            case Constants.WeatherStateAbbr.LIGHT_RAIN:
                resId = R.drawable.ic_lightrain;
                break;
            case Constants.WeatherStateAbbr.SHOWERS:
                resId = R.drawable.ic_showers;
                break;
            case Constants.WeatherStateAbbr.SLEET:
                resId = R.drawable.ic_sleet;
                break;
            case Constants.WeatherStateAbbr.SNOW:
                resId = R.drawable.ic_snow;
                break;
            case Constants.WeatherStateAbbr.THUNDERSTORM:
                resId = R.drawable.ic_thunderstorm;
                break;
            default:
                resId = R.drawable.ic_clear;
                break;
        }
        return resId;
    }
}