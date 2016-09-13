package com.nbhirud.group14_inclass09;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by nbhirud on 6/23/2016.
 */
public class AppDOA {

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference myRef = database.getReference("root");
    DataSnapshot ds;
    static ArrayList<Entry> favApp = new ArrayList<>();
    String userId;


    private SQLiteDatabase db;

    public AppDOA() {
        //this.db = db;
    }

    public void save(Entry app) {

        Log.d("demo","selected :" +app.toString());
        favApp.add(app);
        //DatabaseReference entryRef = myRef.child(app.getId()).getRef();
        myRef.child(MainActivity.userId).child(app.getId()).setValue(app);
//        entryRef.child(AppTable.COLUMN_APPNAME).setValue(app.getAppName());
//        entryRef.child(AppTable.COLUMN_CATEGORY).setValue(app.getCategory());
//        entryRef.child(AppTable.COLUMN_DEVNAME).setValue(app.getDeveloperName());
//        entryRef.child(AppTable.COLUMN_PRICE).setValue(app.getPrice());
//        entryRef.child(AppTable.COLUMN_RELDATE).setValue(app.getReleaseDate());
//        entryRef.child(AppTable.COLUMN_APPURL).setValue(app.getAppURL());
//        entryRef.child(AppTable.COLUMN_IMGURL53).setValue(app.getImageURL53());
//        entryRef.child(AppTable.COLUMN_IMGURL100).setValue(app.getImageURL100());
       // entryRef.child("newApp").setValue(app);

        //Log.d("demo",values.toString());

       // return db.insert(AppTable.TABLENAME, null, values);
    }
/*
    public boolean update(Entry app) {

        ContentValues values = new ContentValues();
        values.put(AppTable.COLUMN_ID, app.getId());
        values.put(AppTable.COLUMN_APPNAME, app.getAppName());
        values.put(AppTable.COLUMN_DEVNAME, app.getDeveloperName());
        values.put(AppTable.COLUMN_PRICE, app.getPrice());
        values.put(AppTable.COLUMN_RELDATE, app.getReleasedate());
        values.put(AppTable.COLUMN_IMGURL53, app.getImageURL53());
        values.put(AppTable.COLUMN_IMGURL100, app.getImageURL100());

        return db.update(AppTable.TABLENAME, values,AppTable.COLUMN_ID + "=?", new String[] {app.getId() + ""}) > 0;
    }
*/
    public void delete(Entry note) {
        favApp.remove(note);
       myRef.child(MainActivity.userId).child(note.getId()).removeValue();


        //return db.delete(AppTable.TABLENAME, AppTable.COLUMN_ID + "=?", new String[] {note.getId() + ""}) > 0;

    }
/*
    public Entry get(long id) {

        Entry app = null;
        Cursor c = db.query(true, AppTable.TABLENAME, new String[] {AppTable.COLUMN_ID, AppTable.COLUMN_APPNAME, AppTable.COLUMN_DEVNAME, AppTable.COLUMN_RELDATE, AppTable.COLUMN_CATEGORY, AppTable.COLUMN_PRICE, AppTable.COLUMN_APPURL, AppTable.COLUMN_IMGURL53, AppTable.COLUMN_IMGURL100}, AppTable.COLUMN_ID + "=?", new String[] {id+""}, null, null, null, null);
        if(c != null && c.moveToFirst()) {
            app = buildNoteFromCursor(c);
            if(!c.isClosed()) {
                c.close();
            }
        }

        return app;
    }
*/
    public ArrayList<Entry> getAll() {

        //ArrayList<Entry> apps = new ArrayList<>();


        //ds = database.getReference("root").child("ll");
        Log.d("demo_fav",favApp.toString());
        return favApp;
    }
/*
    private Entry buildNoteFromCursor(Cursor c) {
        Entry app = null;
        if(c != null) {
            app = new Entry();
            app.setId(c.getString(0).trim());
            app.setAppName(c.getString(1).trim());
            app.setDeveloperName(c.getString(2).trim());
            app.setReleasedate(c.getString(3).trim());
            app.setPrice(c.getString(4).trim());
            //Log.d("demo",c.getString(4).trim());
            app.setCategory(c.getString(5).trim());
            app.setAppURL(c.getString(6).trim());
            //Log.d("demo",c.getString(6));
            app.setImageURL53(c.getString(7).trim());
            app.setImageURL100(c.getString(8).trim());
            app.setFav(true);

        }
        return app;
    }
*/
}