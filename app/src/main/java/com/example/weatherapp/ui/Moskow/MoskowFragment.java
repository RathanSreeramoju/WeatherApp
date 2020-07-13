package com.example.weatherapp.ui.Moskow;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.ContentBinding;
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


public class MoskowFragment extends Fragment {

    private Context context;
    private FragmentMontrealBinding montrealBinding;
    private Dialog dialogView;
    private ContentBinding layoutBinding;

    public MoskowFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

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

            Call<Example> call = service.getMoskowWeatherDetails();
            System.out.println("call__" + call);
            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {

                    if (response.isSuccessful()) {
                        ProgressDialog.progressDialog.dismiss();
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

    private void refreshUi(final List<WeatherInfo> list) {

        montrealBinding.tCity.setText(list.get(0).getCityName());
//        montrealBinding.valMinTemp.setText(String.format("%.2f", Float.valueOf(list.get(0).getMinTemp())));
//        montrealBinding.valMaxTemp.setText(String.format("%.2f", Float.valueOf(list.get(0).getMaxTemp())));
        //       montrealBinding.valMaxTemp.setText(String.format("%.2f", Float.valueOf(list.get(5).getMaxTemp()))+"-"+String.format("%.2f", Float.valueOf(list.get(5).getMinTemp())));

        montrealBinding.valActualTemp.setText(String.format("%.2f", Float.valueOf(list.get(5).getActTemp()))+"\u2103");
        montrealBinding.tHum.setText(String.format(getString(R.string.humidity), list.get(0).getHumidity())+"%");
        montrealBinding.tPre.setText(String.format(getString(R.string.predictability), list.get(0).getPredictability())+"%");
//        montrealBinding.ivIcon1.setImageResource(getImageDrawable(list.get(0).getWeatherStateAbbr()));
        montrealBinding.tMin.setText(String.format("%.2f", Float.valueOf(list.get(0).getMinTemp())));
        montrealBinding.tMax.setText(String.format("%.2f", Float.valueOf(list.get(0).getMaxTemp())));
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

        montrealBinding.middle.setText(getDayString1(list.get(0).getDate()));

        montrealBinding.last.setText(getDayString2(""+list.get(0).getDate()));
        //set listener on day.
        montrealBinding.day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setCommonLayout(list,context,getDayString(list.get(1).getDate()));

            }
        });

        montrealBinding.day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setCommonLayout(list,context,getDayString(list.get(2).getDate()));


            }
        });

        montrealBinding.day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setCommonLayout(list,context,getDayString(list.get(3).getDate()));


            }
        });

        montrealBinding.day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setCommonLayout(list,context,getDayString(list.get(4).getDate()));


            }
        });

        montrealBinding.day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setCommonLayout(list,context,getDayString(list.get(5).getDate()));


            }
        });
    }

    private void setCommonLayout(List<WeatherInfo> list, Context context, String dayString) {

//        Toast.makeText(context, "dialog view", Toast.LENGTH_SHORT).show();
        dialogView = new Dialog(context);
        layoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.content,null,false);
//        layoutBinding.setActivity(RegisterFragment.this);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(layoutBinding.getRoot());
        dialogView.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//android.graphics.Color.TRANSPARENT

        dialogView.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        dialogView.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogView.show();

        dialogView.setCancelable(false);

        layoutBinding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });

        cancelPopUp(list,context,dayString);
    }

    private void cancelPopUp(List<WeatherInfo> list, Context context, String dayString) {
//        dialogView.dismiss();


        if(dayString.equals("Tue")){
            setDataToTheLayout(list,1);
        }else if(dayString.equals("Wed")){
            setDataToTheLayout(list,2);
        }else if(dayString.equals("Thu")){
            setDataToTheLayout(list,3);
        }else if(dayString.equals("Fri")){
            setDataToTheLayout(list,4);
        }else if(dayString.equals("Sat")) {
            setDataToTheLayout(list,5);
        }

    }

    private void setDataToTheLayout(List<WeatherInfo> list, int position) {

        layoutBinding.cityName.setText(list.get(position).getCityName());
        layoutBinding.valMinTemp.setText(String.format("%.2f", Float.valueOf(list.get(position).getMinTemp())));
        layoutBinding.valMaxTemp.setText(String.format("%.2f", Float.valueOf(list.get(position).getMaxTemp())));
        layoutBinding.valActualTemp.setText(String.format("%.2f", Float.valueOf(list.get(position).getActTemp()))+"\u2103");

        layoutBinding.humidity.setText(String.format(getString(R.string.airpressure), list.get(position).getAirPressure())+"%");
        layoutBinding.predictability.setText(String.format(getString(R.string.windSpeed), list.get(position).getPredictability())+"%");
        layoutBinding.ivIcon1.setImageResource(getImageDrawable(list.get(position).getWeatherStateAbbr()));



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