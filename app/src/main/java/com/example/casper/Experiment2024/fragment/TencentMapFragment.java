package com.example.casper.Experiment2024.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.casper.Experiment2024.R;

public class TencentMapFragment extends Fragment {

    public TencentMapFragment() {
        super(R.layout.fragment_tencent_map); // 这里需要创建相应的布局文件
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tencent_map, container, false);
    }
}