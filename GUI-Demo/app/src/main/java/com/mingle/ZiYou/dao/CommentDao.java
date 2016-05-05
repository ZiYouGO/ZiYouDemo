package com.mingle.ZiYou.dao;

import com.mingle.ZiYou.bean.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kinney on 2016/4/24.
 */
public class CommentDao {
    private List<Comment> commentList = new ArrayList<Comment>();//所有景区列表

    //通过景点名字获取该景点评论
    public List<Comment> getCommentsByPointId(int pid){
        return null;
    }
    //添加评论
    public void addComment(int pid,String comment){
    }

}
