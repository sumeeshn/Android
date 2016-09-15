package com.example.indra.inclass03_beacon;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sumeesh on 08/09/16.
 */
public class ProductAdapter extends ArrayAdapter<Data> {

    Context mContext;
    int mResource;
    ArrayList<Data> mData;
    ImageView mImg;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ProductAdapter(Context context, int resource, ArrayList<Data> objects) {
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

        Data product = new Data();
        product = mData.get(position);
        Log.d("demo",product.toString());

        TextView pName = (TextView) convertView.findViewById(R.id.textViewName);
        TextView pDiscount = (TextView) convertView.findViewById(R.id.textViewDiscount);
        TextView pPrice = (TextView) convertView.findViewById(R.id.textViewPrice);
        TextView pRegion = (TextView) convertView.findViewById(R.id.textViewRegion);

        String pImg = product.getImg().toString();

        pName.setText(product.getProduct_Name());
        pDiscount.setText(product.getDiscount() + "");
        pPrice.setText(product.getPrice() + "");
        pRegion.setText(product.getRegion());


        mImg = (ImageView) convertView.findViewById(R.id.imageViewProduct);
        Picasso.with(mContext).load("http://inclass01-env.us-west-2.elasticbeanstalk.com/Inclass03/Support_Files/"+product.getImg()).into(mImg);
        return convertView;

    }

}