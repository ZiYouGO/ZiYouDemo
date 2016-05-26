package com.mingle.ZiYou.daoImpl;

import android.content.Context;
import android.widget.Toast;

import com.mingle.ZiYou.bean.Comment;
import com.mingle.ZiYou.bean.Point;
import com.mingle.ZiYou.daoInterface.PointInterface;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Kinney on 2016/4/24.
 */
public class PointDao implements PointInterface{
    private List<Point> pointList=new ArrayList<Point>();
    private Point point=new Point();
    private BmobFile mp3;

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public BmobFile getMp3() {
        return mp3;
    }

    public void setMp3(BmobFile mp3) {
        this.mp3 = mp3;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

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
                setPointList(object);
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
                    setPoint(p);
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
    //通过景点名字获取语音
    public BmobFile getMP3ByPointName(final String pointName, final Context context){
//        final BmobFile mp3;
        BmobQuery<Point> query=new BmobQuery<Point>();
        query.addWhereEqualTo("pname",pointName);
//查找所需文件
        query.findObjects(context, new FindListener<Point>() {
            @Override
            public void onSuccess(List<Point> list) {
                for (Point p : list) {
                    setMp3(p.getPmp3cn());
                    Toast.makeText(context,"查询成功："+ p.getPmp3cn().getFilename(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(context,"查询失败："+s,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return mp3;
    }
    //根据景点名称下载整个景区语音文件
    public String download(String sname){

        return null;
    }
}
