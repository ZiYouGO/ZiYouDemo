package com.mingle.ZiYou.daoImpl;

import android.content.Context;
import android.widget.Toast;

import com.mingle.ZiYou.bean.Point;
import com.mingle.ZiYou.daoInterface.PointInterface;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Kinney on 2016/4/24.
 */
public class PointDao implements PointInterface{
    private List<Point> pointList=new ArrayList<Point>();
    private Point point=new Point();
    //通过景区id获得该景区所有景点列表
    public  List<Point> getPointDaoListBySceneId(int sceneId, final Context context){
        BmobQuery<Point> pointBmobQuery=new BmobQuery<Point>();
        pointBmobQuery.addWhereEqualTo("sid",sceneId);
        pointBmobQuery.order("pid");
        pointBmobQuery.findObjects(context, new FindListener<Point>() {
            @Override
            public void onSuccess(List<Point> object) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询成功：共"+object.size()+"条数据。",
                        Toast.LENGTH_SHORT).show();
                pointList=object;
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询失败："+msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return pointList;
    }
    //通过景点名字查找景点信息
    public Point getPointByName(String pointName, final Context context){
        BmobQuery<Point> query=new BmobQuery<Point>();
        query.addWhereEqualTo("pname",pointName);
        query.findObjects(context, new FindListener<Point>() {
            @Override
            public void onSuccess(List<Point> object) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询成功：共"+object.size()+"条数据。",
                        Toast.LENGTH_SHORT).show();
                for (Point p : object) {
                    point=p;
                }
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询失败："+msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return point;
    }
    //通过景点名字获取语音,暂定获取的是url
    public String getMP3ByPointName(String ponitName, Context context){

        return null;
    }
}
