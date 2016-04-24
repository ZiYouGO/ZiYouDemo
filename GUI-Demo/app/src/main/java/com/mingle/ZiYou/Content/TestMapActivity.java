package com.mingle.ZiYou.Content;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.mingle.myapplication.R;

public class TestMapActivity extends AppCompatActivity {
    private MapView mapView;
    BaiduMap baiduMap;
    Location location;
    BitmapDescriptor mCurrentMarker;
    MyLocationConfiguration.LocationMode mCurrentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_test_map);*/

        //获取MapView以及BaiduMap，并设置地图类型
        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //定位当前位置
        //开启定位图层
        baiduMap.setMyLocationEnabled(true);
        //构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(0)
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        //设置定位数据
        baiduMap.setMyLocationData(locData);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode,true,mCurrentMarker);
        baiduMap.setMyLocationConfigeration(config);
        //当不需要图层时关闭定位图层
        baiduMap.setMyLocationEnabled(false);
    }
}
