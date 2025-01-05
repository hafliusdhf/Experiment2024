package com.example.casper.Experiment2024.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.casper.Experiment2024.R;
import com.example.casper.Experiment2024.view.SubmarineDodgeBombsView;

public class GameViewFragment extends Fragment {

    private SubmarineDodgeBombsView submarineDodgeBombsView;

    public GameViewFragment() {
        // Required empty public constructor
    }

    public static GameViewFragment newInstance() {
        GameViewFragment fragment = new GameViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!= null) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (submarineDodgeBombsView!= null) {
            submarineDodgeBombsView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (submarineDodgeBombsView!= null) {
            submarineDodgeBombsView.pause();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_view, container, false);
        submarineDodgeBombsView = rootView.findViewById(R.id.game_view);
        return rootView;
    }
}