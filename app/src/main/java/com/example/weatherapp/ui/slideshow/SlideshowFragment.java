package com.example.weatherapp.ui.slideshow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.R;


public class SlideshowFragment extends Fragment {

    NavController navController;

    public SlideshowFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_san, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /*
    private void setDataToTheLayout(List<WeatherInfo> list, int position) {

        layoutBinding.cityName.setText(list.get(position).getCityName());
        layoutBinding.tDate.setText(list.get(position).getDate());
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
     */
}