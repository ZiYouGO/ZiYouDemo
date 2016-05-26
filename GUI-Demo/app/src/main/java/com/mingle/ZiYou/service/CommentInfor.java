package com.mingle.ZiYou.service;

import android.content.Context;

import com.mingle.ZiYou.bean.Comment;
import com.mingle.ZiYou.daoImpl.CommentDao;
import com.mingle.ZiYou.daoImpl.PointDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论相关
 * Created by Kinney on 2016/4/24.
 */
public class CommentInfor {
    private List<Comment> commentList = new ArrayList<Comment>();//所有景区列表
    private CommentDao commentDao = new CommentDao();
    private PointDao pointDao = new PointDao();

    //通过景点名字获取该景点评论
    public List<Comment> getCommentsByPointName(String pointName,Context context){
        int id = pointDao.getPointByName(pointName,context).getPid();
        commentDao.getCommentsByPointId(id,context);
        return commentDao.getComments();
    }
    //添加评论
    public void addCommentByPointName(String pointName,String comment,Context context){
        int id = pointDao.getPointByName(pointName,context).getPid();
        commentDao.addComment(id,comment,context);
    }
}
