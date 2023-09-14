package com.jirafik.weather_app;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WeatherReportModel {
    private long id;
    private String cityName;
    private String weatherDescription;
    private String date;
    private long timezone;
    private long humidity;
    private double windSpeed;
    private double temp;

    public WeatherReportModel(long id, String cityName, String weatherDescription, String date, long timezone, long humidity,
                              double windSpeed, double temp) {
        this.id = id;
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.date = date;
        this.timezone = timezone;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.temp = temp;
    }

    public WeatherReportModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimezone() {
        return timezone;
    }

    public void setTimezone(long timezone) {
        this.timezone = timezone;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        BigDecimal bigDecimalDouble = new BigDecimal(windSpeed);

        this.windSpeed = bigDecimalDouble.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                "id=" + id +
                ", city='" + cityName + '\'' +
                ", description='" + weatherDescription + '\'' +
                ", date='" + date + '\'' +
                ", humidity=" + humidity +
                ", wind=" + windSpeed +
                ", temp=" + temp +
                '}';
    }
}


