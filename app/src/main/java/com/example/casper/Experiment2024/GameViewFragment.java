package com.example.casper.Experiment2024;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.casper.Experiment2024.view.CustomClockView;
import com.example.casper.Experiment2024.view.WhackAMoleView;
import android.view.MotionEvent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameViewFragment extends Fragment {

    private WhackAMoleView whackAMoleView;

    public GameViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WebViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameViewFragment newInstance() {
        GameViewFragment fragment = new GameViewFragment();
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
        whackAMoleView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        whackAMoleView.pause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_view, container, false);
        whackAMoleView = rootView.findViewById(R.id.game_view);
        return rootView;
    }


    public boolean onTouchEvent(MotionEvent event) {
        // 处理触摸事件，例如检测是否击中地鼠
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 检查触摸位置是否击中地鼠
            // 如果击中，调用whackAMoleView.increaseScore();
        }
        return true;
    }
}