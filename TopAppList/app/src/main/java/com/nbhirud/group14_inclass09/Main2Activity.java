package com.nbhirud.group14_inclass09;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by nbhirud on 6/23/2016.
 */
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Serializable img = getIntent().getExtras().getSerializable("IMG_KEY");
       /* Intent i = getIntent();
        Bitmap bmp = (Bitmap) i.getParcelableExtra("IMG_KEY");*/
        String title = getIntent().getExtras().getString("TITLE_KEY");
        String url = getIntent().getExtras().getString("IMG_KEY");
        Boolean b = getIntent().getExtras().getBoolean("STAR");

        Log.d("demo",title+" "+ url);

        TextView tv = (TextView) findViewById(R.id.textViewAppTitle);
        tv.setText(title);

        ImageView iv_app = (ImageView) findViewById(R.id.imageViewLargeImg);
        Picasso.with(this).load(url).into(iv_app);

        ImageView iv_star = (ImageView) findViewById(R.id.imageView_lastScreen);
        if(b==true) {
            iv_star.setImageResource(R.drawable.ic_yellow_star);
        }
        else {
            iv_star.setImageResource(R.drawable.ic_grey_star);
        }

        //iv.setImageBitmap(bmp);*/

    }
}
