package com.josh_davey.university_3rd_year_project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ControlsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controls, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button on = (Button)view.findViewById(R.id.onBtn);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                On_OffAsync turnOn = new On_OffAsync(getContext());
                turnOn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,1);
            }
        });

        Button off = (Button)view.findViewById(R.id.offBtn);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                On_OffAsync turnOff = new On_OffAsync(getContext());
                turnOff.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,0);
            }
        });

    }
}
