package com.jirafik.weather_app;

public class WeatherReportModel {
    //{
    //    "coord": {
    //        "lon": -0.1257,
    //        "lat": 51.5085
    //    },
    //    "weather": [
    //        {
    //            "id": 804,
    //            "main": "Clouds",
    //            "description": "overcast clouds",
    //            "icon": "04d"
    //        }
    //    ],
    //    "base": "stations",
    //    "main": {
    //        "temp": 292.62,
    //        "feels_like": 292.82,
    //        "temp_min": 290.85,
    //        "temp_max": 293.71,
    //        "pressure": 1013,
    //        "humidity": 84
    //    },
    //    "visibility": 10000,
    //    "wind": {
    //        "speed": 3.09,
    //        "deg": 240
    //    },
    //    "clouds": {
    //        "all": 100
    //    },
    //    "dt": 1694416723,
    //    "sys": {
    //        "type": 2,
    //        "id": 2075535,
    //        "country": "GB",
    //        "sunrise": 1694410107,
    //        "sunset": 1694456788
    //    },
    //    "timezone": 3600,
    //    "id": 2643743,
    //    "name": "London",
    //    "cod": 200
    //}

    private long id;
    private String cityName;
    private long timezone;
    private long humidity;
    private double windSpeed;
    private double temp;
    private String weatherDescription;


    public WeatherReportModel(long id, String cityName, long timezone, long humidity, double windSpeed, double temp,
                              String weatherDescription) {
        this.id = id;
        this.cityName = cityName;
        this.timezone = timezone;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.temp = temp;
        this.weatherDescription = weatherDescription;
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
        this.windSpeed = windSpeed;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", timezone=" + timezone +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", temp=" + temp +
                ", weatherDescription='" + weatherDescription + '\'' +
                '}';
    }
}
