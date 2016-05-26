package com.mingle.ZiYou.content;

import android.content.Context;
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
import com.mingle.ZiYou.bean.Point;
import com.mingle.ZiYou.main.MainActivity;
import com.mingle.entity.MenuEntity;
import com.mingle.myapplication.R;
import com.mingle.sweetpick.BlurEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

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
//        setupCustomView();
        getPointDaoListBySceneId(1000);
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
//
//    private void setupCustomView() {
//
//
//
//        mSweetSheet = new SweetSheet(rl);
//
//        final ArrayList<MenuEntity> list =getData();
//        mSweetSheet.setMenuList(list);
//
//        mSweetSheet.setDelegate(new RecyclerViewDelegate(true));
//        //根据设置不同Effect 来显示背景效果BlurEffect:模糊效果.DimEffect 变暗效果
//        mSweetSheet.setBackgroundEffect(new BlurEffect(8));
//        //设置点击事件
//        mSweetSheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
//            @Override
//            public boolean onItemClick(int position, MenuEntity menuEntity1) {
//                //即时改变当前项的颜色
//                if(key==0&&position!=0){
//
//                    list.get(position).titleColor = 0xff5823ff;
//                    list.get(position).iconId=R.drawable.checkbox;
//                    key=1;
//                }else if(key!=0&&position!=0) {
//                    list.get(position).titleColor = 0xff000000;
//                    list.get(position).iconId=R.drawable.checkbox_empty;
//                    key=0;
//                }
//                ((RecyclerViewDelegate) mSweetSheet.getDelegate()).notifyDataSetChanged();
//
//                //根据返回值, true 会关闭 SweetSheet ,false 则不会.
//                Toast.makeText(MapActivity.this, menuEntity1.title + "  " + position, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//
//        mSweetSheet2 = new SweetSheet(rl);
//
//        final ArrayList<MenuEntity> list2 =getData();
//        mSweetSheet2.setMenuList(list2);
//
//        mSweetSheet2.setDelegate(new RecyclerViewDelegate(true));
//        //根据设置不同Effect 来显示背景效果BlurEffect:模糊效果.DimEffect 变暗效果
//        mSweetSheet2.setBackgroundEffect(new BlurEffect(8));
//        //设置点击事件
//        mSweetSheet2.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
//            @Override
//            public boolean onItemClick(int position, MenuEntity menuEntity1) {
//                //即时改变当前项的颜色
//                if (key == 0 && position != 0) {
//
//                    list2.get(position).titleColor = 0xff5823ff;
//                    list2.get(position).iconId = R.drawable.checkbox;
//                    key = 1;
//                } else if (key != 0 && position != 0) {
//                    list2.get(position).titleColor = 0xff000000;
//                    list2.get(position).iconId = R.drawable.checkbox_empty;
//                    key = 0;
//                }
//                ((RecyclerViewDelegate) mSweetSheet.getDelegate()).notifyDataSetChanged();
//
//                //根据返回值, true 会关闭 SweetSheet ,false 则不会.
//                Toast.makeText(MapActivity.this, menuEntity1.title + "  " + position, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//
//        mSweetSheet3 = new SweetSheet(rl);
//
//        final ArrayList<MenuEntity> list3 =getData();
//        mSweetSheet3.setMenuList(list3);
//
//        mSweetSheet3.setDelegate(new RecyclerViewDelegate(true));
//        //根据设置不同Effect 来显示背景效果BlurEffect:模糊效果.DimEffect 变暗效果
//        mSweetSheet3.setBackgroundEffect(new BlurEffect(8));
//        //设置点击事件
//        mSweetSheet3.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
//            @Override
//            public boolean onItemClick(int position, MenuEntity menuEntity1) {
//                //即时改变当前项的颜色
//                if (key == 0 && position != 0) {
//
//                    list3.get(position).titleColor = 0xff5823ff;
//                    list3.get(position).iconId = R.drawable.checkbox;
//                    key = 1;
//                } else if (key != 0 && position != 0) {
//                    list3.get(position).titleColor = 0xff000000;
//                    list3.get(position).iconId = R.drawable.checkbox_empty;
//                    key = 0;
//                }
//                ((RecyclerViewDelegate) mSweetSheet.getDelegate()).notifyDataSetChanged();
//
//                //根据返回值, true 会关闭 SweetSheet ,false 则不会.
//                Toast.makeText(MapActivity.this, menuEntity1.title + "  " + position, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//    }

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

    private  ArrayList getData(List<Point> points){
        final ArrayList<MenuEntity> list = new ArrayList<>();
        //添加假数据
        MenuEntity menuEntity1 = new MenuEntity();
        //menuEntity1.iconId = R.drawable.checkbox_empty;
        menuEntity1.titleColor = 0xff000000;
        menuEntity1.title = "                           确定路线";
        list.add(menuEntity1);
        for (int i=0;i<points.size();i++){
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.iconId = R.drawable.checkbox_empty;
            menuEntity.titleColor = 0xff000000;
            menuEntity.title = points.get(i).getPname();
            list.add(menuEntity);
        }
        return  list;
    }

    //景点列表
    public void getPointDaoListBySceneId(int sceneId){
        BmobQuery<Point> pointBmobQuery=new BmobQuery<Point>();
        pointBmobQuery.addWhereEqualTo("sid",sceneId);
        pointBmobQuery.order("pid");
        pointBmobQuery.findObjects(MapActivity.this, new FindListener<Point>() {
            @Override
            public void onSuccess(List<Point> object) {
                // TODO Auto-generated method stub
                Toast.makeText(MapActivity.this, "查询成功：共" + object.size() + "条数据。",
                        Toast.LENGTH_SHORT).show();
//                setPointList(object);

                mSweetSheet = new SweetSheet(rl);

                final ArrayList<MenuEntity> list =getData(object);
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

                final ArrayList<MenuEntity> list2 =getData(object);
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

                final ArrayList<MenuEntity> list3 =getData(object);
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
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(MapActivity.this, "查询失败：" + msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
//        return pointList;
    }
}
