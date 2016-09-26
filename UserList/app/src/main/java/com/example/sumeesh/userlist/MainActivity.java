package com.example.sumeesh.userlist;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    static int offset = 0;
    static String order = "ASC";
    static String key = "id";
    SwipeRefreshLayout mySwipeRefreshLayout;
    ListView listView;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        v = getLayoutInflater().inflate(R.layout.footer, null);
        listView.removeFooterView(v);
        listView.addFooterView(v);

        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(offset > 0) {
                    offset = offset - 50;
                    new GetJson(MainActivity.this).execute("http://madserver-env.us-west-2.elasticbeanstalk.com/Mad_server/Inclass05/api.php?key=" + key + "&order=" + order + "&offset=" + offset);
                }
                else {
                    new GetJson(MainActivity.this).execute("http://madserver-env.us-west-2.elasticbeanstalk.com/Mad_server/Inclass05/api.php?key=" + key + "&order=" + order + "&offset=" + 0);
                }
                mySwipeRefreshLayout.setRefreshing(false);
            }
        });

        String url = "http://madserver-env.us-west-2.elasticbeanstalk.com/Mad_server/Inclass05/api.php?key=" + key + "&order=" + order + "&offset=" + offset;

        new GetJson(MainActivity.this).execute(url);
    }

    public void action_response(final ArrayList<Person> list){

        // Log.d("demo",list.toString());
        PersonAdapter adapter = new PersonAdapter(this,R.layout.row,list);
        listView.setAdapter(adapter);

        Button loadMore = (Button) findViewById(R.id.buttonLoadMore);
        loadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offset = offset+50;
                new GetJson(MainActivity.this).execute("http://madserver-env.us-west-2.elasticbeanstalk.com/Mad_server/Inclass05/api.php?key=" + key + "&order=" + order + "&offset=" + offset);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.swipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fist_name_asc:
                key = "first_name";
                order = "ASC";
                break;
            case R.id.fist_name_desc:
                key = "first_name";
                order = "DESC";
                break;
            case R.id.last_name_asc:
                key = "last_name";
                order = "ASC";
                break;
            case R.id.last_name_desc:
                key = "last_name";
                order = "DESC";
                break;
            case R.id.gender_asc:
                key = "gender";
                order = "ASC";
                break;
            case R.id.gender_desc:
                key = "gender";
                order = "DESC";
                break;
            case R.id.id_asc:
                key = "id";
                order = "ASC";
                break;
            case R.id.id_desc:
                key = "id";
                order = "DESC";
                break;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }

        String url = "http://madserver-env.us-west-2.elasticbeanstalk.com/Mad_server/Inclass05/api.php?key=" + key + "&order=" + order + "&offset=" + offset;

        new GetJson(MainActivity.this).execute(url);
        return true;


    }

}


