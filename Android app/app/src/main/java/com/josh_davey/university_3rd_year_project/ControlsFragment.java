package com.josh_davey.university_3rd_year_project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ControlsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controls, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText ipInput = (EditText)view.findViewById(R.id.ipInput);
        final EditText portInput = (EditText)view.findViewById(R.id.portInput);


        Button on = (Button)view.findViewById(R.id.onBtn);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ipInput.getText().toString();
                String port = portInput.getText().toString();

                on_offAsync turnOn = new on_offAsync(getContext());
                turnOn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,1,ip,port);
            }
        });

        Button off = (Button)view.findViewById(R.id.offBtn);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ipInput.getText().toString();
                String port = portInput.getText().toString();
                on_offAsync turnOff = new on_offAsync(getContext());
                turnOff.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,0,ip,port);
            }
        });

    }
}
