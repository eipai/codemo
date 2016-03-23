package com.github.eipai.codemo.benchmark.domain;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String username;
    private String email;
    private Date insertTime;
    private String remark;
    private int version;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserInfo [id=");
        builder.append(id);
        builder.append(", username=");
        builder.append(username);
        builder.append(", email=");
        builder.append(email);
        builder.append(", insertTime=");
        builder.append(insertTime);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", version=");
        builder.append(version);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append("]");
        return builder.toString();
    }

}
