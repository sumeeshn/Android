package com.example.sumeesh.madweather;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.io.SerializablePermission;

/**
 * Created by sumeesh on 09/06/16
 */
public class Weather implements Serializable {

    //temperature, humidity, pressure, weather description(s) and weather icon(s)
    String weatherID, weatherDesc, weatherMain, icon, humidity, temperature, pressure;

    public String getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(String weatherID) {
        this.weatherID = weatherID;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Weather [temp = " + temperature + " ,humidity = " + humidity + " ,pressure = " + pressure + " ,description = " + weatherDesc + " ,Icon =" + icon + "]";

    }

}
