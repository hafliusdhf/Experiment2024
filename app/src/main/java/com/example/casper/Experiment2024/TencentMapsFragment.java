package com.example.casper.Experiment2024;

import static android.widget.Toast.LENGTH_LONG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TextureMapView;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;

public class TencentMapsFragment extends Fragment {

    private TextureMapView mapView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tencent_maps, container, false);
        mapView = rootView.findViewById(R.id.mapview_tencent);

        TencentMap tencentMap = mapView.getMap();

        LatLng point1 = new LatLng(22.255453, 113.54145);

        tencentMap.getUiSettings().setZoomGesturesEnabled(true);

        // 设置初始位置和缩放级别
        tencentMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point1,10));

        // 创建 MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions()
                .position(point1) // 设置 Marker 的位置
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.qiqiu)) // 设置 Marker 的图像资源
                .title("暨南大学珠海校区") // 设置 Marker 的标题
                .snippet("这是我们的学校"); // 设置 Marker 的详细信息

        // 添加 Marker 到地图上
        Marker marker = tencentMap.addMarker(markerOptions);

        tencentMap.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getId().equals(marker.getId())) {
                    //自定义Marker被点击
                    Toast.makeText(TencentMapsFragment.this.getContext(),"Your clicked tencent map", LENGTH_LONG).show();
                }
                return true;
            }
        });
//设置Marker支持点击
        marker.setClickable(true);

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
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}