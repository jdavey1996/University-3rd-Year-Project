package com.josh_davey.university_3rd_year_project;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;


public class DetectionHistoryAsync extends AsyncTask<String,String,JSONArray> {
    Context context;
    Activity activity;
    SwipeRefreshLayout sw;
    public DetectionHistoryAsync(Context context, Activity activity, SwipeRefreshLayout sw)
    {
        this.context = context;
        this.activity = activity;
        this.sw = sw;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        SharedPrefs.SharedprefObj device = sharedPrefs.getPrefs();
        String ip = device.ip;
        String port = device.port;

        try{
            URL url = new URL("http://"+ip+":"+port+"/history");

            HttpConnection connection = new HttpConnection();

            JSONArray result = new JSONArray(connection.getData(url));


            return result;

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray data) {
        if (sw!=null) {
            sw.setRefreshing(false);
        }

        if(data == null)
        {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Detection history updated", Toast.LENGTH_SHORT).show();
            Log.i("testdat",data.toString());

            ArrayList<Detection> detectionList = new ArrayList<Detection>();
            for (int i=0; i<data.length();i++)
            {
                try {
                    JSONObject temp = data.getJSONObject(i);
                    detectionList.add(new Detection(temp.getString("id"),temp.getString("date"),temp.getString("time")));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            DetectionHistoryAdapter adapter = new DetectionHistoryAdapter(activity,detectionList);
            ListView detectionsListView = (ListView)activity.findViewById(R.id.detectionsLV);
            detectionsListView.setAdapter(adapter);
        }

    }
}
