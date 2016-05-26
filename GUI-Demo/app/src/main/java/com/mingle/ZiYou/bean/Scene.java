package com.mingle.ZiYou.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by jd on 2016/4/14.
 */
public class Scene extends BmobObject {
    Integer sid;
    String sname;
    String scity;
    BmobFile spicture;
    Integer shot;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getScity() {
        return scity;
    }

    public void setScity(String scity) {
        this.scity = scity;
    }

    public BmobFile getSpicture() {
        return spicture;
    }

    public void setSpicture(BmobFile spicture) {
        this.spicture = spicture;
    }

    public Integer getShot() {
        return shot;
    }

    public void setShot(Integer shot) {
        this.shot = shot;
    }
}
