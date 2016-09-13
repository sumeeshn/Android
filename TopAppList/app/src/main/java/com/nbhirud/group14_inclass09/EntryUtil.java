package com.nbhirud.group14_inclass09;

import android.util.Log;

import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nbhirud on 6/23/2016.
 */
public class EntryUtil {

    static public class EntryPullParser extends DefaultHandler {

        static ArrayList<Entry> entryList = null;
        static Entry entry;
        static DBDataManager dm;
        private static MainActivity activity;



        static ArrayList<Entry> parseEntry(InputStream in, MainActivity a) throws XmlPullParserException, IOException {
            String temp = "";
            activity = a;
            entryList = new ArrayList<>();

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in,"UTF-8");


            int event = parser.getEventType();
            dm = new DBDataManager(activity);
            List<Entry> apps = dm.getAllNotes();
            Log.d("demo_size", apps.size()+" ");

            while (event != XmlPullParser.END_DOCUMENT) {

                /*
                    String appName; //<im:name>Spotify Music</im:name>
    String developerName; //<im:artist href="https://itunes.apple.com/us/developer/spotify-ltd./id324684583?mt=8&uo=2">Spotify Ltd.</im:artist>
    String ReleaseDate; //<im:releaseDate label="July 14, 2011">2011-07-14T04:22:37-07:00</im:releaseDate>
    double price;   //<im:price amount="0.00000" currency="USD">Get</im:price>
    String category; //<category im:id="6014" term="Games" scheme="https://itunes.apple.com/us/genre/ios-games/id6014?mt=8&uo=2" label="Games"/>
    String imageURL; //     <im:image height="53">http://is3.mzstatic.com/image/thumb/Purple18/v4/93/8c/f1/938cf1f7-aec7-9481-fb79-7bb14d6ee5b0/mzl.nautzuix.png/53x53bb-85.png</im:image>

                 */

                switch (event) {
                    case XmlPullParser.START_TAG:

                        if (parser.getName().equals("id")) {
                            entry = new Entry();
                            //<id im:id="324684580" im:bundleId="com.spotify.client">
                            //https://itunes.apple.com/us/app/spotify-music/id324684580?mt=8&uo=2
                            //</id>
                            entry.setId(temp = parser.getAttributeValue(null, "im:id"));
                        }
                        else if (parser.getName().equals("link")) {
                            //<link rel="alternate" type="text/html" href="https://itunes.apple.com/us/app/spotify-music/id324684580?mt=8&uo=2"/>
                            entry.setAppURL(temp = parser.getAttributeValue(null, "href"));
                            //Log.d("demo","setCategory: "+temp);
                            temp = "";
                        }
                        else if(parser.getName().equals("im:name")) {
                            entry.setAppName(temp = parser.nextText().trim());
                            //Log.d("demo","setAppName: "+temp);
                            temp = "";

                            /** NOTE *********************************************************************************************************************************************
                             * The below should be done as per the document, but it felt redundant, so I have read the "im:name" field instead of "title".
                             * ***************************************************************************************************************************************************
                             * else if(parser.getName().equals("title") && entry!=null){
                             entry.setAppName(temp = parser.nextText().trim())
                             * ***************************************************************************************************************************************************
                             */
                        }
                        else if (parser.getName().equals("im:artist")) {

                            entry.setDeveloperName(temp = parser.nextText().trim());
                            //Log.d("demo","setDeveloperName: "+temp);
                            temp = "";
                        }
                        else if (parser.getName().equals("im:releaseDate")) {
                            entry.setReleasedate(temp = parser.getAttributeValue(null, "label"));
                            //Log.d("demo","setReleaseDate: "+temp);
                            temp = "";
                        }
                        else if (parser.getName().equals("im:price")) {
                            entry.setPrice(temp = parser.getAttributeValue(null, "amount")+" "+parser.getAttributeValue(null, "currency"));
                            //Log.d("demo","setPrice: "+temp);
                            temp = "";
                        }
                        else if (parser.getName().equals("category")) {
                            entry.setCategory(temp = parser.getAttributeValue(null, "label"));
                            //Log.d("demo","setCategory: "+temp);
                            temp = "";
                        }
                        else if ((parser.getName().equals("im:image"))) {   //&&(parser.getAttributeValue(null, "height")=="100")) {
                            String height = parser.getAttributeValue(null, "height");
                            /*
                            if(height=="53") {
                                entry.setImageURL53(temp = parser.nextText().trim());
                                Log.d("demo","setImageURL53: "+temp);
                            }
                            else { //if(height=="100") {
                                entry.setImageURL100(temp = parser.nextText().trim());
                                Log.d("demo","setImageURL100: "+temp);
                            }
                            */
                            String temp2 = parser.nextText().trim();
                            entry.setImageURL100(temp2);
                            entry.setImageURL53(temp2);
                            //entry.setImg100(entry.processImage(temp2));
                            //Log.d("demo","setImageURL100: "+temp);

                            temp = "";
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("entry")) {

                            for (Entry f: apps) {
                                Log.d("demo_f",f.toString());
                                if((f.getId()).compareTo(entry.getId()) == 0) {
                                    entry.setFav(true);
                                    Log.d("demo","true");

                                }
                            }


                            entryList.add(entry);
                            entry = null;
                        }
                        break;

                }

                event = parser.next();
            }

            //dm = new DBDataManager(activity);
            //List<Entry> apps = dm.getAllNotes();
            //Log.d("demo",apps.toString());
//            for(Entry e: entryList) {
//                for (Entry f: apps) {
//                    if(f.getId()== e.getId()) {
//                        e.setFav(true);
//                    }
//                }
//            }

            //Log.d("demo_EntryUtil",entryList.toString());
            return entryList;
        }

        public static Entry getEntry() {
            return entry;
        }

    }
}
