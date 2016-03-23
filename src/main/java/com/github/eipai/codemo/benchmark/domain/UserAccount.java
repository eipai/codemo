package com.github.eipai.codemo.benchmark.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private long userId;
    private BigDecimal money;
    private int version;
    private boolean locked;
    private Date insertTime;
    private Date updateTime;
    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserAccount [id=");
        builder.append(id);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", money=");
        builder.append(money);
        builder.append(", version=");
        builder.append(version);
        builder.append(", locked=");
        builder.append(locked);
        builder.append(", insertTime=");
        builder.append(insertTime);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }

}
