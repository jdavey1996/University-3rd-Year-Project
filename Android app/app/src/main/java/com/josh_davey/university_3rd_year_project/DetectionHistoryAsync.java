package com.josh_davey.university_3rd_year_project;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.net.URL;


public class DetectionHistoryAsync extends AsyncTask<String,String,String> {
    Context context;
    public DetectionHistoryAsync(Context context)
    {
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String ip = "192.168.0.27";
        String port = "8080";

        try{
            URL url = new URL("http://"+ip+":"+port+"/history");

            HttpConnection connection = new HttpConnection();

            String result = connection.getData(url);
            return result.trim();

        }catch (Exception e)
        {

        }

        return null;
    }

    @Override
    protected void onPostExecute(String data) {
        if(data == null)
        {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.i("testdat",data.toString());
        }

    }
}
