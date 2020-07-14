package com.example.weatherapp.modelpojoclass;

public class WeatherInfo {

    private String cityName;
    private String minTemp;
    private String actTemp;
    private String maxTemp;
    private String humidity;
    private String predictability;
    private String weatherStateAbbr;
    private String date;
    private String weatherStateName;

    private String windSpeed;
    private String airPressure;

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(String airPressure) {
        this.airPressure = airPressure;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getActTemp() {
        return actTemp;
    }

    public void setActTemp(String actTemp) {
        this.actTemp = actTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPredictability() {
        return predictability;
    }

    public void setPredictability(String predictability) {
        this.predictability = predictability;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public void setWeatherStateAbbr(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }
}
