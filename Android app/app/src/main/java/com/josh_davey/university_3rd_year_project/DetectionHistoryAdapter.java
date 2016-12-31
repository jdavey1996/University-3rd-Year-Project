package com.josh_davey.university_3rd_year_project;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        num.setText(item.getDetectionNum());
        date.setText(item.getDate());
        time.setText(item.getTime());

        return convertView;
    }
}
