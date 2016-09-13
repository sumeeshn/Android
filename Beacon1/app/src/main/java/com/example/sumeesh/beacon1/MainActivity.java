package com.example.sumeesh.beacon1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static android.support.v4.app.NotificationCompatBase.add;

public class MainActivity extends AppCompatActivity {

    private static final Map<String, String> PLACES_BY_BEACONS;
    int flag = 0;
    String regions = null;

//    ListView listView = (ListView) findViewById(R.id.listView);

    // TODO: replace "<major>:<minor>" strings to match your own beacons.
    static {
        Map<String, String> placesByBeacons = new HashMap<>();
        placesByBeacons.put("45153:9209", "Produce");
        placesByBeacons.put("15212:31506", "Grocery");
        placesByBeacons.put("48071:24324", "Lifestyle");
        PLACES_BY_BEACONS = Collections.unmodifiableMap(placesByBeacons);
    }

    private String placesNearBeacon(Beacon beacon) {
        String beaconKey = String.format("%d:%d", beacon.getMajor(), beacon.getMinor());
        if (PLACES_BY_BEACONS.containsKey(beaconKey)) {
            return PLACES_BY_BEACONS.get(beaconKey);
        }
        return "No Beacon Found";
    }

    private BeaconManager beaconManager;
    private Region region;
    TextView beaconName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beaconName = (TextView) findViewById(R.id.Beacon_Name);

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
                    }else if(nearestBeacon.getMajor() == 48071){
                        regions="lifestyle";
                    }else if(nearestBeacon.getMajor() == 45153){
                        regions="produce";
                    }
                    Log.d("demo", "" +regions);
                    String url = "http://inclass01-env.us-west-2.elasticbeanstalk.com/Inclass03/api.php?region=" + regions.toString();
                    beaconName.setText(regions);
                    new GetJson(MainActivity.this).execute(url);

                }
            }
        });
        /*

         */

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


    public void action_response(ArrayList<Product> list){

       // Log.d("demo",list.toString());
        ProductAdapter adapter = new ProductAdapter(this,R.layout.row_layout,list);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}
