package com.github.eipai.codemo.benchmark.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TranRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private List<TransInfoVo> transInfos;

    public TransInfoVo getFirstByOrderId(String orderId) {
        if (null == orderId) return null;
        if (null == transInfos || 0 == transInfos.size()) return null;
        for (TransInfoVo transInfo : transInfos) {
            if (StringUtils.equals(orderId, transInfo.getOrderId())) return transInfo;
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TransInfoVo> getTransInfos() {
        return transInfos;
    }

    public void setTransInfos(List<TransInfoVo> transInfos) {
        this.transInfos = transInfos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TranRequest [id=");
        builder.append(id);
        builder.append(", transInfos=");
        builder.append(transInfos);
        builder.append("]");
        return builder.toString();
    }

}
