package com.nbhirud.group14_inclass09;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nbhirud on 6/23/2016.
 */
public class ParserAsyncTask extends AsyncTask<String, Void, ArrayList<Entry>> {

    MainActivity activity;
    DBDataManager dm;
    ListView lv;
    AppAdapter appAdapter;



    public ParserAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Entry> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                return EntryUtil.EntryPullParser.parseEntry(in,activity);            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(final ArrayList<Entry> result) {
        super.onPostExecute(result);
        if(result != null) {
            //Log.d("demoAll",result.toString());
            //activity.setContentView(R.layout.activity_main);

            appAdapter = new AppAdapter(activity,R.layout.row_layout, result);
            lv = (ListView) activity.findViewById(R.id.listViewApps);
            lv.setAdapter(null);
            lv.setAdapter(appAdapter);
            appAdapter.setNotifyOnChange(true);
            //Log.d("demoResult",result.toString());

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(activity, Main2Activity.class);
                    //i.putExtra("IMG_KEY", result.get(position).getImg100());
                    i.putExtra("IMG_KEY", result.get(position).getImageURL100());
                    i.putExtra("TITLE_KEY", result.get(position).getAppName());
                    i.putExtra("STAR",result.get(position).isFav());
                    activity.startActivity(i);


                }
            });

            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Entry e;
                    dm = new DBDataManager(activity);
                    e = result.get(position);
                    if(e.isFav()) {
                        dm.deleteNote(e);
                    }
                    else {
                        dm.saveNote(e);
                    }
                    e.setFav(!(e.isFav()));
                    Log.d("demoStar", e.isFav()+"");


                    List<Entry> apps = dm.getAllNotes();
                    // Log.d("demofav",apps.toString());
                    // Log.d("demo_all",result.toString());

//                    ImageView imgstar = (ImageView) view.findViewById(R.id.imageView_star);
//                    imgstar.setImageResource(R.drawable.ic_yellow_star);

                    appAdapter = new AppAdapter(activity,R.layout.row_layout, result);
                    lv = (ListView) activity.findViewById(R.id.listViewApps);
                    lv.setAdapter(appAdapter);
                    appAdapter.setNotifyOnChange(true);

                    return true;
                }
            });



            if(AppDOA.myRef.child(MainActivity.userId) != null) {
                AppDOA.myRef.child(MainActivity.userId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot stegoHeightSnapshot) {
                        //Integer favoriteDinoHeight = stegoHeightSnapshot.getValue(Integer.class);
                        Query query = AppDOA.myRef.orderByChild("id");
                        AppDOA.myRef.child(MainActivity.userId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                ArrayList<Entry> appEntry = new ArrayList<>();

                                for(DataSnapshot children : dataSnapshot.getChildren()) {

                                    Entry app1 = children.getValue(Entry.class);
                                    //Log.d("demo", "Fav - " + app1.toString());
                                    app1.setFav(true);
                                    appEntry.add(app1);

                                }
                                //Log.d("demo",appEntry.size()+" items");
                                AppDOA.favApp = appEntry;
                                appEntry = null;


                                // Data is ordered by increasing height, so we want the first entry
//                                if (dataSnapshot != null) {
//                                    if (dataSnapshot.getChildren().iterator().next().getChildrenCount() != 0) {
//                                        DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
//                                        Log.d("demo", "The dinosaur just shorter than the stegosaurus is: " + firstChild.getKey());
//                                    }
//                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // ...
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // ...
                    }
                });
            }


        }
    }

}
