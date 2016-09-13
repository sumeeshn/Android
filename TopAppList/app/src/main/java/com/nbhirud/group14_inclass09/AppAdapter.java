package com.nbhirud.group14_inclass09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nbhirud on 6/23/2016.
 */
public class AppAdapter extends ArrayAdapter<Entry> {

    Context mContext;
    int mResource;
    ArrayList<Entry> mData;
    ImageView i_appicon;
    ImageView i_star;


    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public AppAdapter(Context context, int resource, ArrayList<Entry> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        Entry app = new Entry();
        app = mData.get(position);
        //Log.d("demo",app.toString());

        TextView titleName = (TextView) convertView.findViewById(R.id.textViewTitle);
        TextView devName = (TextView) convertView.findViewById(R.id.textViewDevName);
        TextView category = (TextView) convertView.findViewById(R.id.textViewCat);
        TextView relDate = (TextView) convertView.findViewById(R.id.textViewRelDate);
        TextView price = (TextView) convertView.findViewById(R.id.textViewPrice);

        //String img = app.getImgUrl().toString();
        titleName.setText(app.getAppName());
        devName.setText(app.getDeveloperName());
        category.setText(app.getCategory());
        relDate.setText(app.getReleasedate());
        price.setText(app.getPrice());
        i_appicon = (ImageView) convertView.findViewById(R.id.imageViewApp);
        Picasso.with(mContext).load(app.getImageURL100()).into(i_appicon);


        i_star = (ImageView) convertView.findViewById(R.id.imageView_star);
        Picasso.with(mContext).load(app.getStarImage()).into(i_star);

        return convertView;

    }
        /*
        TextView colorName = (TextView) convertView.findViewById(R.id.textViewName);
        colorName.setText(color.colorName);

        TextView colorHex = (TextView) convertView.findViewById(R.id.textViewHex);
        colorHex.setText(color.colorHex);

        colorName.setTextColor(android.graphics.Color.parseColor(color.colorHex));
        colorHex.setTextColor(android.graphics.Color.parseColor(color.colorHex));
        */

}