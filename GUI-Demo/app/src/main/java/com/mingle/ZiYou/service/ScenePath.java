package com.mingle.ZiYou.service;

import com.mingle.ZiYou.bean.Point;
import com.mingle.ZiYou.daoImpl.PointDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 景区路线相关
 * Created by Kinney on 2016/4/24.
 */
public class ScenePath {
    private PointDao pointDao = new PointDao();
    private List<Point> pointSelectedList = new ArrayList<Point>();//选中路线景点列表
    private List<Point> pathPoint = new ArrayList<Point>();//规划好的路线的景点列表
    private Point pointSelected = new Point();

    //添加路线中选中的点
    public void setPointSelectedList(List<Point> pointList){
        pointSelectedList = pointList;
    }
    //获取路线中选中的点列表
    public List<Point> getPointSelectedList(){
        return pointSelectedList;
    }
    //添加路线中选中的点
    public void setPathPoint(List<Point> pointList){
        pathPoint = pointList;
    }
    //获取规划好的路线的顺序点
    public List<Point> getPathPoint(){
        return pathPoint;
    }
}
