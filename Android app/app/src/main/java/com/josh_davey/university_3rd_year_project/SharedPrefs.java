package com.josh_davey.university_3rd_year_project;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Josh on 29/12/2016.
 */

public class SharedPrefs {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public SharedPrefs(Context context) {
        prefs = context.getSharedPreferences("device_settings", MODE_PRIVATE);
        editor = prefs.edit();
    }

    public SharedprefObj getPrefs()
    {
        String ip = prefs.getString("device_ip", null);
        String port = prefs.getString("device_port", null);
        return new SharedprefObj(ip,port);
    }

    public void updatePrefs(String ip_val,String port_val)
    {
        editor.putString("device_ip",ip_val);
        editor.putString("device_port",port_val);
        editor.apply();
    }

    class SharedprefObj
    {
        String ip;
        String port;

        public SharedprefObj(String ip, String port)
        {
            this.ip = ip;
            this.port = port;
        }
    }
}
