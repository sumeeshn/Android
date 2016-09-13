package com.example.sumeesh.beacon1;

/**
 * Created by sumeesh on 07/09/16.
 */

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

public class MyApplication extends Application{
    private BeaconManager beaconManager;

    @Override
    public void onCreate() {
        super.onCreate();

        beaconManager = new BeaconManager(getApplicationContext());

//        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
//            @Override
//            public void onEnteredRegion(Region region, List<Beacon> list) {
//
//                Log.d("reo - list", list.size() + " , " + list.get(0).getRssi() + " , " + list.get(0).getMeasuredPower() + " ," + list.get(0).getMajor());
//
//                if (list.get(0).getMajor() == 15212) {
//                    showNotification(
//                            "Entered Grocery Region",
//                            "Tap for list of Products "
//                    );
//                }
//                if (list.get(0).getMajor() == 48071) {
//                    showNotification(
//                            "Entered Lifestyle Region",
//                            "Tap for list of Products "
//                    );
//                }
//                if (list.get(0).getMajor() == 45153) {
//                    showNotification(
//                            "Entered Produce Region",
//                            "Tap for list of Products "
//                    );
//                }
////                showNotification(
////                        "Entered Region",
////                        "Tap for list of Products "
////                );
//            }
//
//            @Override
//            public void onExitedRegion(Region region) {
//
//            }
//        });

//        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                beaconManager.startMonitoring(new Region(
//                        "monitored region",
//                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
//                        null, null
//                ));
//            }
//        });
    }

    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[] {notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

}
