package com.mingle.ZiYou.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Kinney on 2016/4/24.
 */
public class Comment extends BmobObject {
    private Integer cgrade;
    private String ctext;
    private Integer pid;

    public Integer getCgrade() {
        return cgrade;
    }

    public void setCgrade(Integer cgrade) {
        this.cgrade = cgrade;
    }

    public String getCtext() {
        return ctext;
    }

    public void setCtext(String ctext) {
        this.ctext = ctext;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
