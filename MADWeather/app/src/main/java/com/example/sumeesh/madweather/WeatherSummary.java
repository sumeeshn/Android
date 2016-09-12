package com.example.sumeesh.madweather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WeatherSummary extends AppCompatActivity {

    TextView temp, humidity, pressure, weather, ic;
    Bitmap icon;
    MainActivity activity;
    ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_summary);

        //String items[] = getResources().getStringArray(R.array.toppings);
        ArrayList<Weather> item = (ArrayList<Weather>) getIntent().getSerializableExtra(MainActivity.RESULT);
        String units = getIntent().getStringExtra("SWITCH");

        //Log.d("demo",units);

        String t = item.get(0).getTemperature();
        String h = item.get(0).getHumidity();
        String p = item.get(0).getPressure();
        String[] w = {item.get(0).getWeatherDesc(),item.get(0).getWeatherMain(),item.get(0).getWeatherID()};
        String i = item.get(0).getIcon();
        String[] links = null;

        temp = (TextView) findViewById(R.id.textViewTemp);
        humidity = (TextView) findViewById(R.id.textViewHumidity);
        pressure = (TextView) findViewById(R.id.textViewPressure);
        weather = (TextView) findViewById(R.id.textViewDesc);

        if(units.equals("&units=metric")) {
            temp.setText("Temperature: " + t + " Celsius");
        } else {
            temp.setText("Temperature: " + t + " Fahrenheit");
        }

        humidity.setText("Humidity: " + h + "%");
        pressure.setText("Pressure: " + p + "hpa");
        weather.setText("Weather: " + w[0] + ", " +w[1]);

        new GetImageAsync().execute("http://openweathermap.org/img/w/" + i +".png");

        //links[0] = "http://openweathermap.org/img/w/" + i +".png";

        //links[1] = "http://openweathermap.org/img/w/" + w[2] +".png";


    }

    public class GetImageAsync extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {
            URL url = null;
            try {
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap bmp = null;
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img1 = (ImageView) findViewById(R.id.imageView1);
            img1.setImageBitmap(bitmap);
        }
    }
}
