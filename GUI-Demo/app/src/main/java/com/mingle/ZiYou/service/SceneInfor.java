package com.mingle.ZiYou.service;

import android.content.Context;

import com.mingle.ZiYou.bean.Point;
import com.mingle.ZiYou.bean.Scene;
import com.mingle.ZiYou.daoImpl.PointDao;
import com.mingle.ZiYou.daoImpl.SceneDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有景区景点资源信息
 * Created by Kinney on 2016/4/24.
 */
public class SceneInfor {
    private SceneDao sceneDao = new SceneDao();
    private PointDao pointDao = new PointDao();

    //获得所有景区列表
    public List<Scene> getSceneList(Context context) {
        return sceneDao.getAllScenes(context);
    }
    //通过景区名字查找景区信息
    public Scene getSceneByName(String sceneName,Context context){
        return sceneDao.getSceneByName(sceneName,context);
    }
    //通过景区名字获得该景区所有景点列表
    public List<Point> getPointDaoListBySceneName(String sceneName,Context context) {
        //getPointDaoListBySceneName是通过sid查询points
        return pointDao.getPointDaoListBySceneId(getSceneByName(sceneName,context).getSid(),context);
    }
    //通过景点名字查找景点信息
    public Point getPointByName(String ponitName,Context context){
        return pointDao.getPointByName(ponitName,context);
    }
    //通过景点名字获取语音,暂定获取的是url
    public String getMP3ByPointName(String ponitName,Context context){
        return pointDao.getMP3ByPointName(ponitName,context);
    }


}
