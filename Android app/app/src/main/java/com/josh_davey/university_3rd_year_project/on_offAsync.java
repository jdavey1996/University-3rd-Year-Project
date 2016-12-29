package com.josh_davey.university_3rd_year_project;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URL;

public class On_OffAsync extends AsyncTask<Object, String, String> {
    Context context;
    public On_OffAsync(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... params) {
        Integer filter = (Integer) params[0];
        String ip = (String) params[1];
        String port = (String) params[2];

            JSONObject data = new JSONObject();
            try {
                if (filter == 0) {
                    data.put("command", "off");
                } else if (filter == 1) {
                    data.put("command", "on");
                }

                URL url = new URL("http://"+ip+":"+port+"/control");

                HttpConnection connection = new HttpConnection();

                String result = connection.postData(url,data);
                return result.trim();
            }catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        //If result is null, error occurred.
        /*Else response codes:
            000 = turned off
            001 = turned on
            002 = already off
            003 = already on*/
        if(result==null)
        {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
        else {
            switch (result) {
                case "000":
                    Toast.makeText(context, "Turned off!", Toast.LENGTH_SHORT).show();
                    break;
                case "001":
                    Toast.makeText(context, "Turned on!", Toast.LENGTH_SHORT).show();
                    break;
                case "002":
                    Toast.makeText(context, "Already off!", Toast.LENGTH_SHORT).show();
                    break;
                case "003":
                    Toast.makeText(context, "Already on!", Toast.LENGTH_SHORT).show();
                    break;


            }
        }
    }
}
