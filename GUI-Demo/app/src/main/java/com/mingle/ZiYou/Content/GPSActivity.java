package com.mingle.ZiYou.Content;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
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
import com.mingle.ZiYou.Main.MainActivity;
import com.mingle.ZiYou.broadandservice.SoundService;
import com.mingle.myapplication.R;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class GPSActivity extends AppCompatActivity {
    //UI相关
    private MapView mapView;
    private BaiduMap baiduMap;
    private RoutePlanSearch mSearch, mSearch2;
    Button mBtn, mRoadplanBtn;

    //定位相关
    public LocationClient mLocationClient = null;
    private static final int UPDATE_TIME = 30000;//间隔时间之后重新获取定位
    public BDLocationListener myListener = new MyLocationListener();//监听器
    BitmapDescriptor mCurrentMarker;//定位小图标
    MyLocationConfiguration.LocationMode mCurrentMode;//定位模式
    boolean isFirstLoc = true;//是否是首次定位
    //手机振动
    //Vibrator mVibrator01 = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    //音乐
    private Boolean musicState = true;
    //注册位置提醒监听事件后，可以通过SetNotifyLocation 来修改位置提醒设置，修改后立刻生效。
    //BDNotifyListner实现
//    public class NotifyLister extends BDNotifyListener {
//        public void onNotify(BDLocation mlocation, float distance){
//            //mVibrator01.vibrate(1000);//振动提醒已到设定位置附近
//            Toast.makeText(GPSActivity.this, "位置提醒响应", Toast.LENGTH_SHORT).show();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_gps);

        //后台播放音乐
        mBtn = (Button)findViewById(R.id.btn_gps_sound);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GPSActivity.this, SoundService.class);
                intent.putExtra("playing", musicState);
                musicState = !musicState;
                startService(intent);
            }
        });

        //线路规划
        //-----------------------------------------------------------------------------
        //导航按钮事件
        mRoadplanBtn = (Button)findViewById(R.id.btn_roadplan);
        mRoadplanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearch = RoutePlanSearch.newInstance();
                mSearch2 = RoutePlanSearch.newInstance();
                OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
                    @Override
                    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                        if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                            WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMap);
                            baiduMap.setOnMarkerClickListener(overlay);
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
                mSearch2.setOnGetRoutePlanResultListener(listener);
                PlanNode stNode2 = PlanNode.withCityNameAndPlaceName("北京", "北京交通大学思源楼");
                PlanNode enNode2 = PlanNode.withCityNameAndPlaceName("北京", "北京交通大学思源西楼");
                mSearch2.walkingSearch((new WalkingRoutePlanOption())
                        .from(stNode2)
                        .to(enNode2));
                //记得这里需要销毁
                //mSearch.destroy();
            }
        });
        //-----------------------------------------------------------------------------

        //获取布局上的MapView组件，并设置地图类型与偏好
        mapView = (MapView) findViewById(R.id.b_gps_mapView);
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        baiduMap.setMyLocationEnabled(true);
        
        LatLng cenpt = new LatLng(39.95799,116.349642);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化


        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        baiduMap.setMapStatus(mMapStatusUpdate);

        //mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
        //mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        //MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode,true,mCurrentMarker);
        //baiduMap.setMyLocationConfigeration(config);

        //定位初始化
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        initLocation();
        mLocationClient.registerLocationListener(myListener);    //注册监听函数

        //开始定位
        mLocationClient.start();
        //mLocationClient.requestLocation();
//        //位置提醒相关代码
//        NotifyLister mNotifyer = new NotifyLister();
//        mNotifyer.SetNotifyLocation(39.957153, 116.351038, 20, "gps");//4个参数代表要位置提醒的点的坐标，具体含义依次为：纬度，经度，距离范围，坐标系类型(gcj02,gps,bd09,bd09ll)
//        mLocationClient.registerNotify(mNotifyer);
//        //取消位置提醒
//        //mLocationClient.removeNotifyEvent(mNotifyer);



    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(UPDATE_TIME);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    //位置监听器
    public class MyLocationListener implements BDLocationListener
    {
        double mLatitude = 39.957153;
        double mLongtitude = 116.351038;
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(location.getLatitude() + "\n" + location.getLongitude());
            Toast.makeText(GPSActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
//            if((location.getLatitude() - mLatitude) < 0.01
//                && (location.getLongitude() - mLongtitude) < 0.01)
//                Toast.makeText(GPSActivity.this, "哈哈步入范围内", Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(GPSActivity.this, "呵呵离开范围内", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
            mLocationClient = null;
        }
    }
}
