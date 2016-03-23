package com.github.eipai.codemo.benchmark.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String orderId;
    private BigDecimal amount;
    private long payer;
    private long receiver;
    /** 1001:3.1|1002:4.9 */
    private String receiversInfo;
    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getPayer() {
        return payer;
    }

    public void setPayer(long payer) {
        this.payer = payer;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public String getReceiversInfo() {
        return receiversInfo;
    }

    public void setReceiversInfo(String receiversInfo) {
        this.receiversInfo = receiversInfo;
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
        builder.append("TransInfoVo [id=");
        builder.append(id);
        builder.append(", orderId=");
        builder.append(orderId);
        builder.append(", amount=");
        builder.append(amount);
        builder.append(", payer=");
        builder.append(payer);
        builder.append(", receiver=");
        builder.append(receiver);
        builder.append(", receiversInfo=");
        builder.append(receiversInfo);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }

}
