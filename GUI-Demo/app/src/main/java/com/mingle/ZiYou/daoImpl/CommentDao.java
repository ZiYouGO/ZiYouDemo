package com.mingle.ZiYou.daoImpl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.platform.comapi.map.C;
import com.mingle.ZiYou.bean.Comment;
import com.mingle.ZiYou.daoInterface.CommentInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.FindStatisticsListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Kinney on 2016/4/24.
 */
public class CommentDao implements CommentInterface {
    List<Comment> comments=new ArrayList<Comment>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    //通过景点id获取该景点评论
    public List<Comment> getCommentsByPointId(int pid, final Context context){
        //我们按游戏名统计所有玩家的总得分，并只返回总得分大于100的记录，并按时间降序

        BmobQuery<Comment> query = new BmobQuery<Comment>();
        query.addWhereGreaterThanOrEqualTo("cgrade",5);
        query.order("-cgrade,createdAt");
        query.findObjects(context, new FindListener<Comment>() {
            @Override
            public void onSuccess(List<Comment> object) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询成功：共"+object.size()+"条数据。",
                        Toast.LENGTH_SHORT).show();
                for (Comment comment : object) {
                    comments.add(comment);
                }
                setComments(comments);
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(context,"查询失败："+msg,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return comments;
    }
    //添加评论
    public void addComment(int pid, String comment, final Context context){
        Comment new_comment = new Comment();
        new_comment.setCgrade(1);
        new_comment.setCtext(comment);
        new_comment.setPid(pid);
        final boolean flag=false;
        new_comment.save(context, new SaveListener() {

            @Override
            public void onSuccess() {
                Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int code, String arg0) {
                // 添加失败
                Toast.makeText(context,"添加失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
