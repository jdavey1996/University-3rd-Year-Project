package com.josh_davey.university_3rd_year_project;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by Josh on 30/12/2016.
 */

public class DetectionHistoryAdapter extends ArrayAdapter<Detection> {

    public DetectionHistoryAdapter(Activity activity, ArrayList<Detection> detectionsList) {
        super(activity, 0, detectionsList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater taskInflater = LayoutInflater.from(getContext());
        if (convertView == null) {
            convertView = taskInflater.inflate(R.layout.detection, parent, false);
        }

        //Get current list item.
        final Detection item = getItem(position);

        TextView num = (TextView)convertView.findViewById(R.id.detectionNumber);
        TextView date = (TextView)convertView.findViewById(R.id.detectionDate);
        TextView time = (TextView)convertView.findViewById(R.id.detectionTime);
        ImageView img = (ImageView)convertView.findViewById(R.id.detectionImg);

        num.setText(item.getDetectionNum());
        date.setText(item.getDate());
        time.setText(item.getTime());

        try {

            byte[] imgByteArray = Base64.decode(item.getImg(), Base64.DEFAULT);
            Bitmap imgBitmap = BitmapFactory.decodeByteArray(imgByteArray, 0, imgByteArray.length);
            img.setImageBitmap((Bitmap.createScaledBitmap(imgBitmap,img.getWidth(),img.getHeight(),false)));
        }catch (Exception e)
        {

        }


        return convertView;
    }
}
