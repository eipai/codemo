package com.github.eipai.codemo.benchmark.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.github.eipai.codemo.benchmark.bean.CdType;

public class AccountDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private long tranId;
    private int indx;
    private long acctId;
    private BigDecimal amount;
    private CdType cdType;
    private BigDecimal balance;
    private long relatedAcctId;
    private String remark;
    private Date insertTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTranId() {
        return tranId;
    }

    public void setTranId(long tranId) {
        this.tranId = tranId;
    }

    public int getIndx() {
        return indx;
    }

    public void setIndx(int indx) {
        this.indx = indx;
    }

    public long getAcctId() {
        return acctId;
    }

    public void setAcctId(long acctId) {
        this.acctId = acctId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CdType getCdType() {
        return cdType;
    }

    public void setCdType(CdType cdType) {
        this.cdType = cdType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getRelatedAcctId() {
        return relatedAcctId;
    }

    public void setRelatedAcctId(long relatedAcctId) {
        this.relatedAcctId = relatedAcctId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AccountDetail [id=");
        builder.append(id);
        builder.append(", tranId=");
        builder.append(tranId);
        builder.append(", indx=");
        builder.append(indx);
        builder.append(", acctId=");
        builder.append(acctId);
        builder.append(", amount=");
        builder.append(amount);
        builder.append(", cdType=");
        builder.append(cdType);
        builder.append(", balance=");
        builder.append(balance);
        builder.append(", relatedAcctId=");
        builder.append(relatedAcctId);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", insertTime=");
        builder.append(insertTime);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append("]");
        return builder.toString();
    }

}
