package com.mingle.ZiYou.daoImpl;


import android.content.Context;
import android.widget.Toast;

import com.mingle.ZiYou.bean.Scene;
import com.mingle.ZiYou.daoInterface.SceneInterface;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Kinney on 2016/4/24.
 */
public class SceneDao implements SceneInterface {
    private List<Scene> sceneList = new ArrayList<Scene>();//所有景区列表
    private Scene new_scene=new Scene();
    //获得所有景区列表
    public List<Scene> getAllScenes(final Context context){
        BmobQuery<Scene> sceneBmobQuery=new BmobQuery<Scene>();
        sceneBmobQuery.findObjects(context, new FindListener<Scene>() {
            @Override
            public void onSuccess(List<Scene> object) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询成功：共"+object.size()+"条数据。",
                        Toast.LENGTH_SHORT).show();
                for (Scene scene : object) {
                    sceneList.add(scene);
                }
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询失败："+msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return sceneList;
    }
    //通过景区名字获得景区
    public Scene getSceneByName(String name, final Context context){
        BmobQuery<Scene> sceneBmobQuery=new BmobQuery<Scene>();
        sceneBmobQuery.addWhereEqualTo("sname",name);
        sceneBmobQuery.findObjects(context, new FindListener<Scene>() {
            @Override
            public void onSuccess(List<Scene> object) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询成功：共"+object.size()+"条数据。",
                        Toast.LENGTH_SHORT).show();
                for (Scene scene : object) {
                    new_scene=scene;
                }
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询失败："+msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return new_scene;
    }

}
