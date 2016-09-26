package com.example.sumeesh.userlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by sumeesh on 22/09/16.
 */
public class PersonAdapter extends ArrayAdapter<Person>{

    Context mContext;
    int mResource;
    ArrayList<Person> mData;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public PersonAdapter(Context context, int resource, ArrayList<Person> objects) {
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

        Person person = new Person();
        person = mData.get(position);
        Log.d("demo",person.toString());

        TextView pId = (TextView) convertView.findViewById(R.id.textViewId);
        TextView pFirstName = (TextView) convertView.findViewById(R.id.textViewFirstName);
        TextView pLastName = (TextView) convertView.findViewById(R.id.textViewLastName);
        TextView pEmail = (TextView) convertView.findViewById(R.id.textViewEmail);
        TextView pGender = (TextView) convertView.findViewById(R.id.textViewGender);
        TextView pIp = (TextView) convertView.findViewById(R.id.textViewIp);

        pId.setText(person.getId() + "");
        pFirstName.setText(person.getFirstName());
        pLastName.setText(person.getLastName());
        pEmail.setText(person.getEmail());
        pIp.setText(person.getIp());
        pGender.setText(person.getGender());


        return convertView;

    }

}
