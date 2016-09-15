package com.example.indra.inclass03_beacon;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by indra on 6/9/16.
 */
public class JsonParse extends AsyncTask<String,Void,ArrayList<Data>> {

    MainActivity activity;
    ArrayList<Data> data_list = new ArrayList<>();
    Data data;


            JsonParse(MainActivity activity){
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Data> data_list) {

        super.onPostExecute(data_list);
     //  Log.d("demo1",weather.getCode()+"");


        activity.action_response(data_list);


    }

    @Override
    protected ArrayList<Data> doInBackground(String... params) {


        JSONObject root  = null;

        try {
            root = new JSONObject(params[0]);

           JSONArray json_data = root.getJSONArray("data");

            for(int i=0; i<json_data.length(); i++) {
                data =new Data();
                JSONObject json_object = json_data.getJSONObject(i);
                data.setDiscount(json_object.getInt("discount"));
                data.setImg(json_object.getString("photo"));
                data.setProduct_Name(json_object.getString("name"));
                data.setPrice((float)json_object.getDouble("price"));
                data.setRegion(json_object.getString("region"));

                data_list.add(data);
            }

                return data_list;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
