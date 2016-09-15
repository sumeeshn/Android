package com.example.indra.inclass03_beacon;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by indra on 6/9/16.
 */
public class GetJson extends AsyncTask<String,Void,String> {
    BufferedReader reader=null;

    MainActivity activity;


    GetJson(MainActivity activity){
        this.activity = activity;
    }
    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
       Log.d("demo1",s);

        new JsonParse(activity).execute(s);
    }

    @Override
    protected String doInBackground(String... params) {

        try {


            URL url = new URL(params[0]);
            Log.d("demo1",url+"");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int status = con.getResponseCode();
            Log.d("demo", status+"");
                if(status == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line="";
                    while((line= reader.readLine())!=null){
                        sb.append(line).append("\n");
                    }

                    return sb.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
