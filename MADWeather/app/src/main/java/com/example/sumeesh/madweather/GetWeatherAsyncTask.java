package com.example.sumeesh.madweather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sumeesh on 09/06/16.
 */
public class GetWeatherAsyncTask extends AsyncTask<String, Void, ArrayList<Weather>> {
    MainActivity act;
    ProgressDialog pd;
    ArrayList<Weather> resultW;

    public GetWeatherAsyncTask(MainActivity act) {
        this.act = act;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(act);
        pd.setCancelable(false);
        pd.setMessage("Loading..");
        pd.show();

    }

    @Override
    protected ArrayList<Weather> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();

                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }

                //Log.d("demo",sb.toString());

                return WeatherUtil.WeatherJSONParser.parseWeather(sb.toString());

                //return PersonUtil.PersonJSONParser.parsePersons(sb.toString());

            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Weather> result) {
        super.onPostExecute(result);
        if(result!=null) {
            pd.dismiss();
            Log.d("demo",result.toString());
        }
        Intent intent = new Intent(act, WeatherSummary.class);
        intent.putExtra("RESULT",result);
        intent.putExtra("SWITCH",act.unitsPart);
        act.startActivity(intent);
    }
}
