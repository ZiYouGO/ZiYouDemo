package com.mingle.ZiYou.service;

import android.content.Context;

import com.mingle.ZiYou.bean.Point;
import com.mingle.ZiYou.bean.Scene;
import com.mingle.ZiYou.daoImpl.PointDao;
import com.mingle.ZiYou.daoImpl.SceneDao;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;

/**
 * 所有景区景点资源信息
 * Created by Kinney on 2016/4/24.
 */
public class SceneInfor {
    private SceneDao sceneDao = new SceneDao();
    private PointDao pointDao = new PointDao();

    //获得所有景区列表
    public List<Scene> getSceneList(Context context) {
        sceneDao.getAllScenes(context);
        return sceneDao.getSceneList();
    }
    //通过景区名字查找景区信息
    public Scene getSceneByName(String sceneName,Context context){
        sceneDao.getSceneByName(sceneName,context);
        return sceneDao.getNew_scene();
    }
    //通过景区名字获得该景区所有景点列表
    public List<Point> getPointDaoListBySceneName(String sceneName,Context context) {
        //getPointDaoListBySceneName是通过sid查询points
        pointDao.getPointDaoListBySceneId(getSceneByName(sceneName,context).getSid(),context);
        return pointDao.getPointList();
    }
    //通过景点名字查找景点信息
    public Point getPointByName(String ponitName,Context context){
        pointDao.getPointByName(ponitName,context);
        return pointDao.getPoint();
    }
    //通过景点名字获取语音,暂定获取的是url
    public BmobFile getMP3ByPointName(String ponitName, Context context){
        pointDao.getMP3ByPointName(ponitName,context);
        return pointDao.getMp3();
    }


}
