package com.example.sumeesh.beacon1;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sumeesh on 08/09/16.
 */
public class ParserAsyncTask extends AsyncTask<String, Void, ArrayList<Product>> {

    MainActivity activity;

    public ParserAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Product> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                //return EntryUtil.EntryPullParser.parseEntry(in);
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(final ArrayList<Product> result) {
        super.onPostExecute(result);
        if(result != null) {
            //Log.d("demo",result.toString());
            //activity.setContentView(R.layout.activity_main);
            ProductAdapter appAdapter = new ProductAdapter(activity,R.layout.row_layout, result);
            ListView lv = (ListView) activity.findViewById(R.id.listView);
            lv.setAdapter(appAdapter);
            appAdapter.setNotifyOnChange(true);

        }
    }

}
