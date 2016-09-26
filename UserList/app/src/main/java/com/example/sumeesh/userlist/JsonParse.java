package com.example.sumeesh.userlist;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sumeesh on 22/09/16.
 */
public class JsonParse extends AsyncTask<String,Void,ArrayList<Person>> {

    MainActivity activity;
    ArrayList<Person> data_list = new ArrayList<>();
    Person data;


    JsonParse(MainActivity activity){
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Person> data_list) {

        super.onPostExecute(data_list);

        activity.action_response(data_list);


    }

    @Override
    protected ArrayList<Person> doInBackground(String... params) {


        JSONObject root  = null;

        try {
            root = new JSONObject(params[0]);

            JSONArray json_data = root.getJSONArray("data");

            for(int i=0; i<json_data.length(); i++) {
                data =new Person();
                JSONObject json_object = json_data.getJSONObject(i);
                data.setId(json_object.getInt("id"));
                data.setFirstName(json_object.getString("first_name"));
                data.setLastName(json_object.getString("last_name"));
                data.setEmail(json_object.getString("email"));
                data.setGender(json_object.getString("gender"));
                data.setIp(json_object.getString("ip_address"));

                data_list.add(data);
            }

            return data_list;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
