package com.mingle.ZiYou.Content;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mingle.entity.MenuEntity;
import com.mingle.myapplication.R;
import com.mingle.sweetpick.BlurEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {

    private SweetSheet mSweetSheet;
    private SweetSheet mSweetSheet2;
    private SweetSheet mSweetSheet3;
    private RelativeLayout rl;
    Button Sheet1;
    Button Sheet2;
    Button Sheet3;
    Button Back;
    MapView mMapView = null;
    int  key=0;
    int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //调用地图组建前使用的一句话
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);

        mMapView = (MapView) findViewById(R.id.bmapView);
        rl = (RelativeLayout) findViewById(R.id.rl);

        Back = (Button) findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapActivity.this.finish();
            }
        });

        setButton();
        setupCustomView();

        //--------------------------------------mxymxymxy-start--------------------------------------------
        //作者：马翔宇
        //内容：gps临近警告

        // 获取系统LocationManager服务
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        // 定义交大东门的大致经度、纬度
//        double longitude = 116.355547;
//        double latitude = 39.954085;
//        // 定义半径（1公里）
//        float radius = 1000;
//        // 定义Intent
//        Intent intent = new Intent(this, ProximityAlertReceiver.class);
//        // 将Intent包装成PendingIntent对象
//        PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);
//        // 添加临近警告
//        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
//
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
//                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
//            locationManager.addProximityAlert(latitude, longitude, radius, -1, pi);
//        }

        //--------------------------------------mxymxymxy-end--------------------------------------------

    }

    private void setButton(){

        Sheet1 = (Button) findViewById(R.id.Sheet1);
        Sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSweetSheet.toggle();
            }
        });

        Sheet2 = (Button) findViewById(R.id.Sheet2);
        Sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSweetSheet2.toggle();
            }
        });

        Sheet3 = (Button) findViewById(R.id.Sheet3);
        Sheet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSweetSheet3.toggle();
            }
        });

    }

    private void setupCustomView() {



        mSweetSheet = new SweetSheet(rl);

        final ArrayList<MenuEntity> list =getData();
        mSweetSheet.setMenuList(list);

        mSweetSheet.setDelegate(new RecyclerViewDelegate(true));
        //根据设置不同Effect 来显示背景效果BlurEffect:模糊效果.DimEffect 变暗效果
        mSweetSheet.setBackgroundEffect(new BlurEffect(8));
        //设置点击事件
        mSweetSheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
            @Override
            public boolean onItemClick(int position, MenuEntity menuEntity1) {
                //即时改变当前项的颜色
                if(key==0&&position!=0){

                    list.get(position).titleColor = 0xff5823ff;
                    list.get(position).iconId=R.drawable.checkbox;
                    key=1;
                }else if(key!=0&&position!=0) {
                    list.get(position).titleColor = 0xff000000;
                    list.get(position).iconId=R.drawable.checkbox_empty;
                    key=0;
                }
                ((RecyclerViewDelegate) mSweetSheet.getDelegate()).notifyDataSetChanged();

                //根据返回值, true 会关闭 SweetSheet ,false 则不会.
                Toast.makeText(MapActivity.this, menuEntity1.title + "  " + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mSweetSheet2 = new SweetSheet(rl);

        final ArrayList<MenuEntity> list2 =getData();
        mSweetSheet2.setMenuList(list2);

        mSweetSheet2.setDelegate(new RecyclerViewDelegate(true));
        //根据设置不同Effect 来显示背景效果BlurEffect:模糊效果.DimEffect 变暗效果
        mSweetSheet2.setBackgroundEffect(new BlurEffect(8));
        //设置点击事件
        mSweetSheet2.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
            @Override
            public boolean onItemClick(int position, MenuEntity menuEntity1) {
                //即时改变当前项的颜色
                if (key == 0 && position != 0) {

                    list2.get(position).titleColor = 0xff5823ff;
                    list2.get(position).iconId = R.drawable.checkbox;
                    key = 1;
                } else if (key != 0 && position != 0) {
                    list2.get(position).titleColor = 0xff000000;
                    list2.get(position).iconId = R.drawable.checkbox_empty;
                    key = 0;
                }
                ((RecyclerViewDelegate) mSweetSheet.getDelegate()).notifyDataSetChanged();

                //根据返回值, true 会关闭 SweetSheet ,false 则不会.
                Toast.makeText(MapActivity.this, menuEntity1.title + "  " + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mSweetSheet3 = new SweetSheet(rl);

        final ArrayList<MenuEntity> list3 =getData();
        mSweetSheet3.setMenuList(list3);

        mSweetSheet3.setDelegate(new RecyclerViewDelegate(true));
        //根据设置不同Effect 来显示背景效果BlurEffect:模糊效果.DimEffect 变暗效果
        mSweetSheet3.setBackgroundEffect(new BlurEffect(8));
        //设置点击事件
        mSweetSheet3.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
            @Override
            public boolean onItemClick(int position, MenuEntity menuEntity1) {
                //即时改变当前项的颜色
                if (key == 0 && position != 0) {

                    list3.get(position).titleColor = 0xff5823ff;
                    list3.get(position).iconId = R.drawable.checkbox;
                    key = 1;
                } else if (key != 0 && position != 0) {
                    list3.get(position).titleColor = 0xff000000;
                    list3.get(position).iconId = R.drawable.checkbox_empty;
                    key = 0;
                }
                ((RecyclerViewDelegate) mSweetSheet.getDelegate()).notifyDataSetChanged();

                //根据返回值, true 会关闭 SweetSheet ,false 则不会.
                Toast.makeText(MapActivity.this, menuEntity1.title + "  " + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    private  ArrayList getData(){
        final ArrayList<MenuEntity> list = new ArrayList<>();
        //添加假数据
        MenuEntity menuEntity1 = new MenuEntity();
        //menuEntity1.iconId = R.drawable.checkbox_empty;
        menuEntity1.titleColor = 0xff000000;
        menuEntity1.title = "                           确定路线";
        list.add(menuEntity1);
        for (int i=0;i<10;i++){
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.iconId = R.drawable.checkbox_empty;
            menuEntity.titleColor = 0xff000000;
            menuEntity.title = "QQ";
            list.add(menuEntity);
        }
        return  list;
    }
}
