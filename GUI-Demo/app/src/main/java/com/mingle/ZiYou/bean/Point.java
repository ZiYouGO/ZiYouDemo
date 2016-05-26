package com.mingle.ZiYou.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by jd on 2016/4/14.
 */
public class Point extends BmobObject {
    Integer pid;
    String pname;
    Integer sid;
    BmobFile pmp3cn;
    BmobFile pmp3en;
    String plong;
    String plat;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public BmobFile getPmp3cn() {
        return pmp3cn;
    }

    public void setPmp3cn(BmobFile pmp3cn) {
        this.pmp3cn = pmp3cn;
    }

    public BmobFile getPmp3en() {
        return pmp3en;
    }

    public void setPmp3en(BmobFile pmp3en) {
        this.pmp3en = pmp3en;
    }

    public String getPlong() {
        return plong;
    }

    public void setPlong(String plong) {
        this.plong = plong;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }
}

