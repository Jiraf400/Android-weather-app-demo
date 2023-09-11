package com.jirafik.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    View lv_weatherReports;
    WeatherDataService weatherDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cityID = findViewById(R.id.btn_getCityId);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityId);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReports = findViewById(R.id.lv_weatherReports);
        weatherDataService = new WeatherDataService(MainActivity.this);

        btn_cityID.setOnClickListener(this::btnCityID);

        btn_getWeatherByID.setOnClickListener(this::btnWeatherByID);


        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You typed " + et_dataInput.getText().toString(),
                        Toast.LENGTH_SHORT).show();
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

    private void btnWeatherByID(View view) {

        weatherDataService.getCityForecastByID("4164138", new WeatherDataService.ForecastByIDResponseListener() {
            @Override
            public void onError(String msg) {
                Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(WeatherReportModel reportModel) {
                Toast.makeText(MainActivity.this, reportModel.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}