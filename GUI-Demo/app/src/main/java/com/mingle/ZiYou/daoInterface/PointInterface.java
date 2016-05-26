package com.mingle.ZiYou.daoInterface;

import android.content.Context;

import com.mingle.ZiYou.bean.Point;

import java.io.File;
import java.util.List;

/**
 * Created by jd on 2016/4/28.
 */
public interface PointInterface {
    //通过景区id获得该景区所有景点列表
    public List<Point> getPointDaoListBySceneId(int sceneId, Context context);
    //通过景点名字查找景点信息
    public Point getPointByName(String ponitName, Context context);
    //通过景点名字获取语音,暂定获取的是url
    public File getMP3ByPointName(String ponitName, Context context);
}
