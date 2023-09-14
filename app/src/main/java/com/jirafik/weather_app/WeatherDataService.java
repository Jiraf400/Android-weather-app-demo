package com.jirafik.weather_app;

import static com.android.volley.Request.Method.GET;

import android.content.Context;
import android.util.Log;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    private final String API_KEY = "1f395ef4f08e67b6a2063b5c23402ded";
    private static final String QUERY_FOR_CITY_ID = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String QUERY_FOR_WEATHER_BY_ID = "https://api.openweathermap.org/data/2.5/forecast?id=";
    private static final String QUERY_FOR_WEATHER_BY_CITY_NAME = "https://api.openweathermap.org/data/2.5/forecast?q=";

    Context context;
    String cityId;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public void getCityID(String cityName, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_CITY_ID + cityName + "&appid=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(GET, url, null,
                response -> {
                    try {
                        cityId = response.getString("id");
                        Log.v("getCityID", "got id: " + cityId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

                    volleyResponseListener.onResponse(cityId);
                },
                error ->
                        volleyResponseListener.onError("Error occurred. Please provide correct city name.")
        );

        SingletonRequest.getInstance(context).addToRequestQueue(request);
    }

    public void getCityForecastByID(String cityID, ForecastByCityResponseListener forecastListener) {

        String url = QUERY_FOR_WEATHER_BY_ID + cityID + "&appid=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(GET, url, null,
                response -> {

                    try {
                        List<WeatherReportModel> weatherReportModelList = fetchDataFromJsonRequest(response);

                        forecastListener.onResponse(weatherReportModelList);

                    } catch (JSONException e) {

                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

                    Log.i("WeatherDataService", "All is working fine.");
                }, error -> {

        });

        SingletonRequest.getInstance(context).addToRequestQueue(request);

    }

    public List<WeatherReportModel> getCityForecastByName(String cityName,
                                                          ForecastByCityResponseListener forecastListener) {

        String url = QUERY_FOR_WEATHER_BY_CITY_NAME + cityName + "&appid=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(GET, url, null,
                response -> {

                    try {
                        List<WeatherReportModel> weatherReportModelList = fetchDataFromJsonRequest(response);

                        forecastListener.onResponse(weatherReportModelList);

                    } catch (JSONException e) {

                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

                    Log.i("WeatherDataService", "All is working fine.");
                }, error -> {

        });

        SingletonRequest.getInstance(context).addToRequestQueue(request);


        return null;
    }

    private List<WeatherReportModel> fetchDataFromJsonRequest(JSONObject request) throws JSONException {

        JSONArray jsonArray = request.getJSONArray("list");

        List<WeatherReportModel> forecastByIDList = new ArrayList<>(); //list to get all days for certain city

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.getString("dt_txt").contains("06:00:00")) {
                forecastByIDList.add(mapToModel(jsonObject, request));
            }
        }

        return forecastByIDList;
    }

    private WeatherReportModel mapToModel(JSONObject jsonObject, JSONObject request) throws JSONException {
        WeatherReportModel weatherReportModel = new WeatherReportModel();

        JSONObject wind = jsonObject.getJSONObject("wind");
        JSONObject main = jsonObject.getJSONObject("main");
        JSONObject city = request.getJSONObject("city");
        JSONObject weatherJsonObject = jsonObject.getJSONArray("weather").getJSONObject(0);

        long cityId = city.getLong("id");
        long cityTimezone = city.getLong("timezone");
        long cityHumidity = main.getLong("humidity");
        double cityWindSpeed = Float.parseFloat(wind.getString("speed"));
        double cityTemp = main.getDouble("temp");
        String cityName = city.getString("name");
        String cityWeatherDesc = weatherJsonObject.getString("description");
        String date = jsonObject.getString("dt_txt");

        weatherReportModel.setId(cityId);
        weatherReportModel.setTimezone(cityTimezone);
        weatherReportModel.setHumidity(cityHumidity);
        weatherReportModel.setWindSpeed(cityWindSpeed);
        weatherReportModel.setTemp(cityTemp);
        weatherReportModel.setCityName(cityName);
        weatherReportModel.setWeatherDescription(cityWeatherDesc);
        weatherReportModel.setDate(date);
        Log.i("SOME_TAG", String.valueOf(weatherReportModel));
        return weatherReportModel;
    }

    public interface VolleyResponseListener {
        void onError(String msg);

        void onResponse(String cityID);
    }

    public interface ForecastByCityResponseListener {
        void onError(String msg);

        void onResponse(List<WeatherReportModel> reportModelList);
    }

}
