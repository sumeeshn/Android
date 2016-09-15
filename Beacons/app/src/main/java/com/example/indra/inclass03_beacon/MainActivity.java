package com.example.indra.inclass03_beacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    static String regions;
    private BeaconManager beaconManager;
    private Region region;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);

        beaconManager = new BeaconManager(this);
        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

        beaconManager = new BeaconManager(this);
// add this below:
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {

                    Beacon nearestBeacon = list.get(0);
                    //  List<String> places = placesNearBeacon(nearestBeacon);
                    // TODO: update the UI here
                    Log.d("Airport", "Nearest places: " + nearestBeacon.getMajor());
                   if(nearestBeacon.getMajor() == 15212){
                       regions="grocery";
                       String url = "http://inclass01-env.us-west-2.elasticbeanstalk.com/Inclass03/api.php?region=grocery";
                       new GetJson(MainActivity.this).execute(url);
                   }else if(nearestBeacon.getMajor() == 48071){
                        regions="lifestyle";
                       String url = "http://inclass01-env.us-west-2.elasticbeanstalk.com/Inclass03/api.php?region=lifestyle";
                       new GetJson(MainActivity.this).execute(url);
                    }else if(nearestBeacon.getMajor() == 45153){
                       regions="produce";
                       String url = "http://inclass01-env.us-west-2.elasticbeanstalk.com/Inclass03/api.php?region=produce";
                       new GetJson(MainActivity.this).execute(url);

                   }

                    tv.setText(regions);

                }else {
                regions="All discounts available";
                String url = "http://inclass01-env.us-west-2.elasticbeanstalk.com/Inclass03/api.php?region=All";
                new GetJson(MainActivity.this).execute(url);
            }

            }
        });


    }
    public void action_response(ArrayList<Data> list){


        Log.d("demo",list.toString());
        ProductAdapter adapter = new ProductAdapter(this,R.layout.row_layout,list);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });



    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);

        super.onPause();
    }
}
