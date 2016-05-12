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
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.mingle.myapplication.R;

public class mxyOwnMapActivity extends AppCompatActivity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private RoutePlanSearch mSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_mxy_own_map);
        //地图控件
        mMapView = (MapView) findViewById(R.id.b_mxy_mapView);
        mBaiduMap = mMapView.getMap();
        //mBaidumap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //导航按钮事件
        Button btn = (Button) findViewById(R.id.btn_mxy_Guide);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearch = RoutePlanSearch.newInstance();
                //mSearch2 = RoutePlanSearch.newInstance();
                OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
                    @Override
                    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                        if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                            WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaiduMap);
                            mBaiduMap.setOnMarkerClickListener(overlay);
                            overlay.setData(walkingRouteResult.getRouteLines().get(0));
                            overlay.addToMap();
                            overlay.zoomToSpan();
                        } else Log.e("mxy", "no result");
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
