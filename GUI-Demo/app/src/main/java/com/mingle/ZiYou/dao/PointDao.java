package com.mingle.ZiYou.dao;

import com.mingle.ZiYou.bean.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kinney on 2016/4/24.
 */
public class PointDao {
    private List<Point> pointList = new ArrayList<Point>();//所有景点列表

    //通过景区id获得该景区所有景点列表
    public  List<Point> getPointDaoListBySceneId(int sceneId){
        return null;
    }
    //通过景点名字查找景点信息
    public Point getPointByName(String ponitName){
        return null;
    }
    //通过景点名字获取语音,暂定获取的是url
    public String getMP3ByPointName(String ponitName){
        return null;
    }
}
