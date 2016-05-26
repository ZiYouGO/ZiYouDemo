package com.mingle.ZiYou.daoInterface;

import android.content.Context;

import com.mingle.ZiYou.bean.Comment;

import java.util.List;

/**
 * Created by jd on 2016/4/28.
 */
public interface CommentInterface {
    //通过景点id获取该景点评论
    public List<Comment> getCommentsByPointId(int pid, Context context);
    //添加评论
    public void addComment(int pid, String comment, Context context);
}
