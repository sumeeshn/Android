package com.example.sumeesh.madweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String RESULT = "RESULT";

    ArrayList<Weather> list;

    String api_link = "http://api.openweathermap.org/data/2.5/forecast/city?";
    EditText location;
    Switch metricSwitch;
    String qPart;
    String unitsPart;
    String appID = "&APPID=8f8486b3274f871635aa15599cef407d";
    String weatherLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location = (EditText) findViewById(R.id.editTextLocation);

        metricSwitch = (Switch) findViewById(R.id.switchMetric);

        if (metricSwitch != null) {
            if(metricSwitch.isChecked()) {
                unitsPart = "&units=metric";
            } else {
                unitsPart = "&units=imperial";
            }
        }


        Button checkWeather = (Button) findViewById(R.id.buttonCheck);

        if (checkWeather != null) {
            checkWeather.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(location.getText() != null) {
                        qPart = "q=" + location.getText().toString();
                    } else {
                        Toast.makeText(MainActivity.this,"Please Enter the Location",Toast.LENGTH_LONG).show();
                    }

                    metricSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(metricSwitch.isChecked()) {
                                unitsPart = "&units=metric";
                            } else {
                                unitsPart = "&units=imperial";
                            }
                        }
                    });

                    weatherLink = api_link + qPart + unitsPart + appID;

                    //Log.d("demo",weatherLink);

                    GetWeatherAsyncTask task = new GetWeatherAsyncTask(MainActivity.this);
                    task.execute(weatherLink);
                    Intent intent = new Intent(MainActivity.this, WeatherSummary.class);
                    //Log.d("demo",task.resultW.toString());
                }
            });
        }

        //http://api.openweathermap.org/data/2.5/forecast/city?q=Charlotte,NC,US&units=imperial&APPID=8f8486b3274f871635aa15599cef407d

    }

}
