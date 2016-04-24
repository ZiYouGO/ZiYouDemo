package com.mingle.ZiYou.service;

import com.mingle.ZiYou.bean.Point;
import com.mingle.ZiYou.dao.PointDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 景区路线相关
 * Created by Kinney on 2016/4/24.
 */
public class ScenePath {
    private PointDao pointDao = new PointDao();
    private List<Point> pointSelectedList = new ArrayList<Point>();//选中路线景点列表
    private Point pointSelected = new Point();

    //添加路线中选中的点
    public List<Point> addPointSelected(String pointName){
        pointSelected = pointDao.getPointByName(pointName);
        pointSelectedList.add(pointSelected);
        return pointSelectedList;
    }
}
