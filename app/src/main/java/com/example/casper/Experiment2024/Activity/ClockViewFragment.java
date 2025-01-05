package com.example.casper.Experiment2024.Activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.casper.Experiment2024.R;
import com.example.casper.Experiment2024.view.CustomClockView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClockViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClockViewFragment extends Fragment {

    private CustomClockView clockView;

    public ClockViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WebViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClockViewFragment newInstance() {
        ClockViewFragment fragment = new ClockViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    public void onResume() {
        super.onResume();
        clockView.startUpdating(); // 重新启动更新
    }

    @Override
    public void onPause() {
        super.onPause();
        clockView.stopUpdating(); // 停止更新
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_clock_view, container, false);
        clockView = rootView.findViewById(R.id.clock_view_time);
        return rootView;
    }
}