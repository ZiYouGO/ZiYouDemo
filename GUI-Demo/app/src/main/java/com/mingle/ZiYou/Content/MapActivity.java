package com.mingle.ZiYou.Content;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.mapapi.map.MapView;
import com.mingle.entity.MenuEntity;
import com.mingle.myapplication.R;
import com.mingle.sweetpick.BlurEffect;
import com.mingle.sweetpick.CustomDelegate;
import com.mingle.sweetpick.DimEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;
import com.mingle.sweetpick.ViewPagerDelegate;

import java.util.ArrayList;
import com.baidu.mapapi.SDKInitializer;

public class MapActivity extends AppCompatActivity {

    private SweetSheet mSweetSheet;
    private SweetSheet mSweetSheet2;
    private SweetSheet mSweetSheet3;
    private RelativeLayout rl;
    Button Sheet;
    Button Back;
    MapView mMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //调用地图组建前使用的一句话
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);

        mMapView = (MapView)findViewById(R.id.bmapView);
        rl = (RelativeLayout) findViewById(R.id.rl);

        Back=(Button)findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapActivity.this.finish();
            }
        });

        Sheet=(Button)findViewById(R.id.Sheet);
        Sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSweetSheet.toggle();
            }
        });

        setupViewpager();
        setupRecyclerView();
        setupCustomView();
    }

    private void setupCustomView() {

        mSweetSheet3 = new SweetSheet(rl);
        CustomDelegate customDelegate = new CustomDelegate(true,
                CustomDelegate.AnimationType.DuangLayoutAnimation);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_custom_view, null, false);
        customDelegate.setCustomView(view);
        mSweetSheet3.setDelegate(customDelegate);
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSweetSheet3.dismiss();
            }
        });
    }

    private void setupRecyclerView() {

        final ArrayList<MenuEntity> list = new ArrayList<>();
        //添加假数据
        MenuEntity menuEntity1 = new MenuEntity();
        menuEntity1.iconId = R.drawable.ic_account_child;
        menuEntity1.titleColor = 0xff000000;
        menuEntity1.title = "code";
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.iconId = R.drawable.ic_account_child;
        menuEntity.titleColor = 0xffb3b3b3;
        menuEntity.title = "QQ";
        list.add(menuEntity1);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        list.add(menuEntity);
        // SweetSheet 控件,根据 rl 确认位置
        mSweetSheet = new SweetSheet(rl);

        //设置数据源 (数据源支持设置 list 数组,也支持从菜单中获取)
        mSweetSheet.setMenuList(list);
        //根据设置不同的 Delegate 来显示不同的风格.
        mSweetSheet.setDelegate(new RecyclerViewDelegate(true));
        //根据设置不同Effect 来显示背景效果BlurEffect:模糊效果.DimEffect 变暗效果
        mSweetSheet.setBackgroundEffect(new BlurEffect(8));
        //设置点击事件
        mSweetSheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
            @Override
            public boolean onItemClick(int position, MenuEntity menuEntity1) {
                //即时改变当前项的颜色
                list.get(position).titleColor = 0xff5823ff;
                ((RecyclerViewDelegate) mSweetSheet.getDelegate()).notifyDataSetChanged();

                //根据返回值, true 会关闭 SweetSheet ,false 则不会.
                Toast.makeText(MapActivity.this, menuEntity1.title + "  " + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }

    private void setupViewpager() {


        mSweetSheet2 = new SweetSheet(rl);

        //从menu 中设置数据源
        mSweetSheet2.setMenuList(R.menu.menu_sweet);
        mSweetSheet2.setDelegate(new ViewPagerDelegate());
        mSweetSheet2.setBackgroundEffect(new DimEffect(0.5f));
        mSweetSheet2.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
            @Override
            public boolean onItemClick(int position, MenuEntity menuEntity1) {

                Toast.makeText(MapActivity.this, menuEntity1.title + "  " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mSweetSheet.isShow() || mSweetSheet2.isShow()) {
            if (mSweetSheet.isShow()) {
                mSweetSheet.dismiss();
            }
            if (mSweetSheet2.isShow()) {
                mSweetSheet2.dismiss();
            }
        } else {
            super.onBackPressed();
        }


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
}
