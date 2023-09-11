package com.jirafik.weather_app;

import static com.android.volley.Request.Method.GET;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {

    private final String API_KEY = "1f395ef4f08e67b6a2063b5c23402ded";
    private static final String QUERY_FOR_CITY_ID = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String QUERY_FOR_WEATHER_BY_ID = "https://api.openweathermap.org/data/2.5/weather?id=";

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


    public void getCityForecastByID(String cityID, ForecastByIDResponseListener forecastListener) {

        String url = QUERY_FOR_WEATHER_BY_ID + cityID + "&appid=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(GET, url, null,
                response -> {

                    try {
                        WeatherReportModel weatherReportModel = fetchDataFromJsonRequest(response);

                        forecastListener.onResponse(weatherReportModel);

                    } catch (JSONException e) {
                        Log.e("WDService", response.toString());
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

                    Log.i("WeatherDataService", "All is working fine.");

//                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                }, error -> {

        });

        SingletonRequest.getInstance(context).addToRequestQueue(request);

    }

//    public List<WeatherReportModel> getCityForecastByName(String cityName) {
//        return null;
//    }

    private WeatherReportModel fetchDataFromJsonRequest(JSONObject request) throws JSONException {

        JSONObject wind = request.getJSONObject("wind");
        JSONObject main = request.getJSONObject("main");
        JSONArray weather = request.getJSONArray("weather");
        JSONObject weatherJsonObject = weather.getJSONObject(0);

        long cityId = request.getLong("id");
        long cityTimezone = request.getLong("timezone");
        long cityHumidity = main.getLong("humidity");
        double cityWindSpeed = Float.parseFloat(wind.getString("speed"));
        double cityTemp = main.getDouble("temp");
        String cityName = request.getString("name");
        String cityWeatherDesc = weatherJsonObject.getString("description");

        WeatherReportModel reportModel = new WeatherReportModel();

        reportModel.setId(cityId);
        reportModel.setTimezone(cityTimezone);
        reportModel.setHumidity(cityHumidity);
        reportModel.setWindSpeed(cityWindSpeed);
        reportModel.setTemp(cityTemp);
        reportModel.setCityName(cityName);
        reportModel.setWeatherDescription(cityWeatherDesc);

        return reportModel;
    }

    public interface VolleyResponseListener {
        void onError(String msg);

        void onResponse(String cityID);
    }

    public interface ForecastByIDResponseListener {
        void onError(String msg);

        void onResponse(WeatherReportModel reportModel);
    }

}
