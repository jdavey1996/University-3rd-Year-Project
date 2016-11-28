package com.josh_davey.university_3rd_year_project;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class Firebase_Instance_ID_Service extends FirebaseInstanceIdService{

    @Override
    public void onTokenRefresh() {
        //When token is refreshed or acquired for first time, store and log token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Firebase_IID_Service", "Refreshed token: " + refreshedToken);
    }
}
