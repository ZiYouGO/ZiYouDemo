package com.mingle.ZiYou.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mingle.ZiYou.adapter.MyAdapter;
import com.mingle.ZiYou.bean.Scene;
import com.mingle.ZiYou.content.MapActivity;
import com.mingle.ZiYou.content.TestMapActivity;
import com.mingle.ZiYou.service.SceneInfor;
import com.mingle.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private List<Scene> scenes=new ArrayList<Scene>();
    private  ListView listView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "c1c024e7612cd05fcfecbc3d9909b3ee");
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView=(ListView)findViewById(R.id.list);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        setSupportActionBar(toolbar);
        getAllScenes();
        Log.i("size:1",scenes.size()+"");
        List<Map<String, Object>> list=getData(scenes);
        listView.setAdapter(new MyAdapter(this, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(MainActivity.this, MapActivity.class);
                //测试用，启动TestMapActivity
                Intent intent = new Intent(MainActivity.this, TestMapActivity.class);
                startActivity(intent);
            }
        });

        drawer.setDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);

        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<Map<String, Object>> getData(List<Scene> scenes){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        SceneInfor sceneInfor = new SceneInfor();
//        List<Scene> scenes = sceneInfor.getSceneList(MainActivity.this);
        for (int i = 0; i < scenes.size(); i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image", R.drawable.demo_list);
            map.put("title", scenes.get(i).getSname());
            map.put("info", "information"+i);
            list.add(map);
        }
        return list;
    }

    /*
    获取数据
     */

    //获取景区列表
    public void getAllScenes(){
        BmobQuery<Scene> sceneBmobQuery=new BmobQuery<Scene>();
        sceneBmobQuery.findObjects(MainActivity.this, new FindListener<Scene>() {
            @Override
            public void onSuccess(List<Scene> object) {
                // TODO Auto-generated method stub
                List<Scene> sceneList = new ArrayList<Scene>();
                Toast.makeText(MainActivity.this,"查询成功：共"+object.size()+"条数据。",
                        Toast.LENGTH_SHORT).show();
                for (Scene scene : object) {
                    sceneList.add(scene);
                }
//                getData(sceneList);
                scenes=sceneList;
                Log.i("size:2",scenes.size()+"");
                List<Map<String, Object>> list=getData(scenes);
                listView.setAdapter(new MyAdapter(MainActivity.this, list));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Intent intent = new Intent(MainActivity.this, MapActivity.class);
                        //测试用，启动TestMapActivity
                        Intent intent = new Intent(MainActivity.this, TestMapActivity.class);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this,"查询失败："+msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
//        return sceneList;
    }
}
