package com.example.casper.Experiment2024.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.casper.Experiment2024.R;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;

public class TencentMapFragment extends Fragment {
    private MapView mapView;
    private TencentMap tencentMap;

    public TencentMapFragment() {
        super(R.layout.fragment_tencent_map); // 这里需要创建相应的布局文件
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tencent_map, container, false);
        mapView = rootView.findViewById(R.id.map_view);
        if (mapView == null) {
            Log.e("TencentMapFragment", "MapView is null, please check the layout or initialization process.");
            return rootView;
        }
        tencentMap = mapView.getMap();

        // 设置地图中心和缩放级别
        LatLng jnuLocation = new LatLng(22.2711, 113.5767); // 暨南大学珠海校区的经纬度
        tencentMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jnuLocation, 18)); // 最大缩放级别

        // 添加标记
        com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions markerOptions = new MarkerOptions()
                .position(jnuLocation)
                .title("暨南大学珠海校区");
        Marker marker = tencentMap.addMarker(markerOptions);

        // 设置标记点击事件
        tencentMap.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getActivity(), "点击了: " + marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false; // 返回false表示继续处理点击事件
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}