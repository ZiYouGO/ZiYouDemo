package com.mingle.ZiYou.service;

import com.mingle.ZiYou.bean.Point;
import com.mingle.ZiYou.bean.Scene;
import com.mingle.ZiYou.dao.PointDao;
import com.mingle.ZiYou.dao.SceneDao;

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
    public List<Scene> getSceneList() {
        return sceneDao.getAllScenes();
    }
    //通过景区名字查找景区信息
    public Scene getSceneByName(String sceneName){
        return sceneDao.getSceneByName(sceneName);
    }
    //通过景区名字获得该景区所有景点列表
    public List<Point> getPointDaoListBySceneName(String sceneName) {
        //getPointDaoListBySceneName是通过sid查询points
        return pointDao.getPointDaoListBySceneId(getSceneByName(sceneName).getSid());
    }
    //通过景点名字查找景点信息
    public Point getPointByName(String ponitName){
        return pointDao.getPointByName(ponitName);
    }
    //通过景点名字获取语音,暂定获取的是url
    public String getMP3ByPointName(String ponitName){
        return pointDao.getMP3ByPointName(ponitName);
    }


}
