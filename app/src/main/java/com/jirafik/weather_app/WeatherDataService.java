package com.jirafik.weather_app;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherDataService {

    private final String API_KEY = "1f395ef4f08e67b6a2063b5c23402ded";
    private static final String QUERY_FOR_CITY_ID = "https://api.openweathermap.org/data/2.5/weather?q=";
    Context context;
    String cityId;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String msg);

        void onResponse(String cityID);
    }

    public void getCityID(String cityName, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_CITY_ID + cityName + "&appid=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        cityId = response.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

//                    Toast.makeText(context, "City ID = " + cityId, Toast.LENGTH_SHORT).show();
                    volleyResponseListener.onResponse(cityId);
                },
                error -> {
//                    Toast.makeText(context, "Error occurred", Toast.LENGTH_LONG).show();
                    volleyResponseListener.onError("Error occurred");
                });

        SingletonRequest.getInstance(context).addToRequestQueue(request);

//        return cityId;
    }

//    public List<WeatherReport> getCityForecastByID(String cityID) {
//        return null;
//    }
//
//    public List<WeatherReportModel> getCityForecastByName(String cityName) {
//        return null;
//    }


}
