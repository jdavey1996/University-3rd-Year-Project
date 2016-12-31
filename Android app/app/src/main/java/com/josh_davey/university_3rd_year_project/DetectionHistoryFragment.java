package com.josh_davey.university_3rd_year_project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetectionHistoryFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detection_history, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadDetectionHistory(null);

        final SwipeRefreshLayout sw = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDetectionHistory(sw);
            }
        });
    }

    public void loadDetectionHistory(SwipeRefreshLayout sw)
    {
        DetectionHistoryAsync detectionHistoryAsync = new DetectionHistoryAsync(getContext(),getActivity(),sw);
        detectionHistoryAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}

