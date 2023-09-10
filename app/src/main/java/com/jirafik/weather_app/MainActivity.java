package com.jirafik.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    View lv_weatherReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cityID = findViewById(R.id.btn_getCityId);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityId);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReports = findViewById(R.id.lv_weatherReports);
        btn_cityID.setOnClickListener(view -> {

            WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

            weatherDataService.getCityID(
                    et_dataInput.getText().toString(),
                    new WeatherDataService.VolleyResponseListener() {
                        @Override
                        public void onError(String msg) {
                            Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String cityID) {
                            Toast.makeText(MainActivity.this, "Returned CityID " + cityID, Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked me 2.", Toast.LENGTH_SHORT).show();
            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "You typed " + et_dataInput.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }


}