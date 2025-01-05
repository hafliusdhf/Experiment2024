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
import com.example.casper.Experiment2024.data.ShopDownLoader;
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

        // 设置默认位置
        LatLng jnuLocation = new LatLng(22.255453, 113.54145);
        tencentMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jnuLocation, 18));
        tencentMap.getUiSettings().setZoomGesturesEnabled(true);

        // 下载商店数据并显示
        new ShopDownLoader.DownloadTask() {
            @Override
            protected void onPostExecute(ArrayList<ShopLocation> shopLocations) {
                for (ShopLocation shop : shopLocations) {
                    LatLng shopLocation = new LatLng(shop.getLatitude(), shop.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(shopLocation)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.download)) // 替换为您的图标
                            .title(shop.getName());
                    tencentMap.addMarker(markerOptions);
                }
            }
        }.execute("https://prod-files-secure.s3.us-west-2.amazonaws.com/3500cdb4-2af2-4803-b797-b49933980410/8332c677-d2bd-461f-97da-5a84839038f4/shop.json");

        Button zoomInButton = rootView.findViewById(R.id.zoomInButton);
        Button zoomOutButton = rootView.findViewById(R.id.zoomOutButton);
        zoomInButton.setOnClickListener(v -> tencentMap.animateCamera(CameraUpdateFactory.zoomIn()));
        zoomOutButton.setOnClickListener(v -> tencentMap.animateCamera(CameraUpdateFactory.zoomOut()));

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