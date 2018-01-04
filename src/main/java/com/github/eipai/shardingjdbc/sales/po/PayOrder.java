package com.github.eipai.shardingjdbc.sales.po;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PayOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String requestNo;
    private String bankSeq;
    private long amount;
    private String remark;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getBankSeq() {
        return bankSeq;
    }

    public void setBankSeq(String bankSeq) {
        this.bankSeq = bankSeq;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
