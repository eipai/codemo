package com.github.eipai.codemo.benchmark.domain;

import java.io.Serializable;
import java.util.Date;

public class SeqGenerator implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String code;
    private boolean recurring;
    private long latestValue;
    private long beginValue;
    private long endValue;
    private long step;
    private Date insertTime;
    private Date updateTime;
    private int version;
    private String remark;

    public transient long currentMaxValue;

    public static enum SeqGeneratorCode {
        tran_info, tran_detail
    }

    public long generateNextValue() {
        return latestValue++;
    }

    public boolean checkValue() {
        if (latestValue > currentMaxValue) return false;
        return true;
    }

    public boolean updateNextValue() {
        if (latestValue + step <= endValue) {
            latestValue = latestValue + step;
        } else {
            if (!recurring) return false;
            latestValue = beginValue + step;
        }
        return true;
    }

    public void updateLocalValue() {
        currentMaxValue = latestValue - 1;
        latestValue = latestValue - step;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public long getLatestValue() {
        return latestValue;
    }

    public void setLatestValue(long latestValue) {
        this.latestValue = latestValue;
    }

    public long getBeginValue() {
        return beginValue;
    }

    public void setBeginValue(long beginValue) {
        this.beginValue = beginValue;
    }

    public long getEndValue() {
        return endValue;
    }

    public void setEndValue(long endValue) {
        this.endValue = endValue;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long step) {
        this.step = step;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
        builder.append("SeqGenerator [id=");
        builder.append(id);
        builder.append(", code=");
        builder.append(code);
        builder.append(", recurring=");
        builder.append(recurring);
        builder.append(", latestValue=");
        builder.append(latestValue);
        builder.append(", beginValue=");
        builder.append(beginValue);
        builder.append(", endValue=");
        builder.append(endValue);
        builder.append(", step=");
        builder.append(step);
        builder.append(", insertTime=");
        builder.append(insertTime);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append(", version=");
        builder.append(version);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", currentMaxValue=");
        builder.append(currentMaxValue);
        builder.append("]");
        return builder.toString();
    }

}
