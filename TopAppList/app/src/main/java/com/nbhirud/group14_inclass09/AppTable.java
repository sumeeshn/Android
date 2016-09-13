package com.nbhirud.group14_inclass09;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nbhirud on 6/23/2016.
 */
public class AppTable {
    static final String TABLENAME = "FavoriteApps";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_APPNAME = "App_Name";
    static final String COLUMN_DEVNAME = "Dev_Name";
    static final String COLUMN_RELDATE = "Rel_Date";
    static final String COLUMN_CATEGORY = "Category";
    static final String COLUMN_PRICE = "Price";
    static final String COLUMN_APPURL = "AppURL";
    static final String COLUMN_IMGURL53 = "IMG53";
    static final String COLUMN_IMGURL100 = "IMG100";



    static public void onCreate(SQLiteDatabase db) {

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + "(");
        sb.append(COLUMN_ID + " text primary key, ");
        sb.append(COLUMN_APPNAME + " text not null, ");
        sb.append(COLUMN_DEVNAME + " text not null, ");
        sb.append(COLUMN_RELDATE + " text not null, ");
        sb.append(COLUMN_CATEGORY + " text not null, ");
        sb.append(COLUMN_PRICE + " text not null, ");
        sb.append(COLUMN_APPURL + " text not null, ");
        sb.append(COLUMN_IMGURL53 + " text not null, ");
        sb.append(COLUMN_IMGURL100 + " text not null );");
        try {
            db.execSQL(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        String test = "CREATE TABLE " + TABLENAME + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_SUBJECT + " text not null, " + COLUMN_TEXT + " text not null );";
//                db.execSQL(test);
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        AppTable.onCreate(db);

    }

}