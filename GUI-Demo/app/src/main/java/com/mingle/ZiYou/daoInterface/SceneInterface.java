package com.mingle.ZiYou.daoInterface;

import android.content.Context;

import com.mingle.ZiYou.bean.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jd on 2016/4/28.
 */
public interface SceneInterface {
    //获得景区所有列表
    public List<Scene> getAllScenes(Context context);
    //通过景区名字获得景区
    public Scene getSceneByName(String name, Context context);
}
