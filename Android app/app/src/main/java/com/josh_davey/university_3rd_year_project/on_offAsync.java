package com.josh_davey.university_3rd_year_project;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URL;

public class on_offAsync extends AsyncTask<Object, String, Boolean> {
    Context context;
    public on_offAsync(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Object... params) {
        Integer filter = (Integer) params[0];
        String ip = (String) params[1];
        String port = (String) params[2];
        Log.i("aaaa",ip);
        Log.i("aaaa",port);
            JSONObject data = new JSONObject();
            try {
                if (filter == 0) {
                    data.put("command", "off");
                } else if (filter == 1) {
                    data.put("command", "on");
                }

                URL url = new URL("http://"+ip+":"+port);
                Log.i("aaaa",url.toString());

                HttpConnection connection = new HttpConnection();

                Log.i("result",connection.postData(url,data));

                //!!!Check if result is good before returning true.

                return true;
            }catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        //If result is false, error occurred.
        if(!result)
        {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
        }
    }
}
