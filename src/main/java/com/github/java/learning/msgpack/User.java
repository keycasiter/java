package com.github.java.learning.msgpack;

import org.msgpack.annotation.Message;

import java.util.Date;

/**
 * created by guanjian on 2020/12/29 15:56
 */
@Message
public class User {
    private Integer pId;
    private String pName;
    private Date birthday;
    private Boolean isMarry;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isMarry() {
        return isMarry;
    }

    public void setIsMarry(boolean isMarry) {
        this.isMarry = isMarry;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Boolean getIsMarry() {
        return isMarry;
    }

    public void setIsMarry(Boolean isMarry) {
        this.isMarry = isMarry;
    }

    @Override
    public String toString() {
        return "User{" +
                "birthday=" + birthday +
                ", pId=" + pId +
                ", pName='" + pName + '\'' +
                ", isMarry=" + isMarry +
                '}';
    }
}
