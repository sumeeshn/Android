package com.nbhirud.group14_inclass09;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_all, btn_fav;
    static String userId;
    DBDataManager dm;
    Boolean imgResult = false;
    ArrayList<Entry> appList;
   // FirebaseDatabase database;
    //DatabaseReference myRef;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        btn_all = (Button) findViewById(R.id.all_menu);
        btn_fav = (Button) findViewById(R.id.fav_menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.all_menu:
                Toast.makeText(MainActivity.this,"all",Toast.LENGTH_SHORT).show();
                new ParserAsyncTask(MainActivity.this).execute("http://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");
                break;
            case R.id.fav_menu:


                Toast.makeText(MainActivity.this,"fav",Toast.LENGTH_SHORT).show();
                ListView lv = (ListView) findViewById(R.id.listViewApps);
                lv.setAdapter(null);
                AppAdapter adapter;

                DBDataManager dm = new DBDataManager(this);
                appList = dm.getAllNotes();
                adapter = new AppAdapter(this, R.layout.row_layout, appList);
                lv.setAdapter(adapter);
                adapter.setNotifyOnChange(true);
                Toast.makeText(MainActivity.this,"fav",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = getIntent().getExtras().getString("USERID_KEY");
        Log.d("demo", "" + userId);
        new ParserAsyncTask(MainActivity.this).execute("http://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");
    }

}

