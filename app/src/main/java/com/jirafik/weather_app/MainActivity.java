package com.jirafik.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_cityID, btnGetForecastByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReport;
    WeatherDataService weatherDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cityID = findViewById(R.id.btn_getCityId);
        btnGetForecastByID = findViewById(R.id.btn_getWeatherByCityId);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReports);
        weatherDataService = new WeatherDataService(MainActivity.this);

        btn_cityID.setOnClickListener(this::btnCityID);

        btnGetForecastByID.setOnClickListener(this::btnForecastByID);

        btn_getWeatherByName.setOnClickListener(this::btnForecastByCityName);

    }

    private void btnForecastByCityName(View view) {
        weatherDataService.getCityForecastByName(et_dataInput.getText().toString(),
                new WeatherDataService.ForecastByCityResponseListener() {
                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> reportModels) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                                android.R.layout.simple_list_item_1, reportModels);
                        lv_weatherReport.setAdapter(arrayAdapter);
                    }
                });
    }

    private void btnCityID(View view) {
        String cityNameFromInput = et_dataInput.getText().toString();
        weatherDataService.getCityID(cityNameFromInput,
                new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, "Returned CityID " + cityID, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void btnForecastByID(View view) {

        weatherDataService.getCityForecastByID(et_dataInput.getText().toString(),
                new WeatherDataService.ForecastByCityResponseListener() {
                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> reportModels) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                                android.R.layout.simple_list_item_1, reportModels);
                        lv_weatherReport.setAdapter(arrayAdapter);
                    }
                });


    }
}