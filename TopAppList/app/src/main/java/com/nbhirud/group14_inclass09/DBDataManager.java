package com.nbhirud.group14_inclass09;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by nbhirud on 6/23/2016.
 */
public class DBDataManager {

    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private AppDOA appDOA;

    public DBDataManager(Context mContext) {
        this.mContext = mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        appDOA = new AppDOA();
    }

    public void close() {
        if(db != null) {
            db.close();
        }
    }

    public AppDOA getNoteDAO() {
        return this.appDOA;
    }

    public void saveNote(Entry app) {
        this.appDOA.save(app);
    }

//    public boolean updateNote(Entry app) {
//        return this.appDOA.update(app);
//    }

    public void deleteNote(Entry app) {
        this.appDOA.delete(app);
    }

//    public Entry getNote(long id) {
//        return this.appDOA.get(id);
//    }

    public ArrayList<Entry> getAllNotes() {
        return this.appDOA.getAll();
    }

}