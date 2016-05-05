package com.mingle.ZiYou.Content;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.*;
import com.mingle.myapplication.R;

import java.util.List;

public class TestMapActivity extends AppCompatActivity {
    MapView mMapView;
    RoutePlanSearch mSearch;
    RoutePlanSearch mSearch2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_test_map);
        //地图控件
        mMapView = (MapView)findViewById(R.id.b_test_mapView);
        final BaiduMap mBaidumap = mMapView.getMap();
        //mBaidumap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //导航按钮事件
        Button btn = (Button)findViewById(R.id.btn_Guide);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearch = RoutePlanSearch.newInstance();
                //mSearch2 = RoutePlanSearch.newInstance();
                OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
                    @Override
                    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                        if(walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR){
                            WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaidumap);
                            mBaidumap.setOnMarkerClickListener(overlay);
                            overlay.setData(walkingRouteResult.getRouteLines().get(0));
                            overlay.addToMap();
                            overlay.zoomToSpan();
                        }
                        else Log.e("mxy", "no result");
                    }

                    @Override
                    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

                    }

                    @Override
                    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

                    }

                    @Override
                    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

                    }
                };
                mSearch.setOnGetRoutePlanResultListener(listener);
                PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "北京交通大学逸夫楼");
                PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "北京交通大学思源楼");
                mSearch.walkingSearch((new WalkingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
                //记得这里需要销毁
                //mSearch.destroy();
            }
        });
    }
}
