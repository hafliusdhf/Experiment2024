package com.example.casper.Experiment2024.Activity;

import static android.widget.Toast.LENGTH_LONG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.example.casper.Experiment2024.R;
import com.example.casper.Experiment2024.data.DataLoader;
import com.example.casper.Experiment2024.data.ShopLocation;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TextureMapView;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;

import java.util.ArrayList;

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

        Button zoomInButton = rootView.findViewById(R.id.zoomInButton);
        Button zoomOutButton = rootView.findViewById(R.id.zoomOutButton);

        zoomInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tencentMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        zoomOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tencentMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });

        LatLng point1 = new LatLng(22.255453, 113.54145);

        tencentMap.getUiSettings().setZoomGesturesEnabled(true);

        // 设置初始位置和缩放级别
        tencentMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point1,15));

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 执行耗时操作
                DataLoader dataLoader=new DataLoader();
                String jsonFileContent= dataLoader.getTextFromUrl("https://file.notion.so/f/f/3500cdb4-2af2-4803-b797-b49933980410/8332c677-d2bd-461f-97da-5a84839038f4/shop.json?table=block&id=143148a1-26c1-8090-acdf-d425c3687691&spaceId=3500cdb4-2af2-4803-b797-b49933980410&expirationTimestamp=1732147200000&signature=52MHNgN50dLi6fbnVeu8cET0UbchVFKBukqjoEHCnVY&downloadName=shop.json");

                ArrayList<ShopLocation> locations= dataLoader.parseLocations(jsonFileContent);

                if(null==TencentMapsFragment.this.getActivity())return;
                // 在主线程中更新UI
                TencentMapsFragment.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (ShopLocation shop: locations
                        ) {
                            LatLng point1 = new LatLng(shop.getLatitude(), shop.getLongitude());
                            // 创建 MarkerOptions
                            MarkerOptions markerOptions = new MarkerOptions()
                                    .position(point1) // 设置 Marker 的位置
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.qiqiu)) // 设置 Marker 的图像资源
                                    .title(shop.getName()) // 设置 Marker 的标题
                                    .snippet(shop.getMemo()); // 设置 Marker 的详细信息

                            // 添加 Marker 到地图上
                            Marker marker = tencentMap.addMarker(markerOptions);
                            //设置Marker支持点击
                            marker.setClickable(true);

                        }
                        //textView.setText(result);
                    }
                });
            }
        }).start();

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