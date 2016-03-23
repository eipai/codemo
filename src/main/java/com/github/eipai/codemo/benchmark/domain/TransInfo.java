package com.github.eipai.codemo.benchmark.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String orderId;
    private BigDecimal amount;
    private long payer;
    private long receiver;
    /** 1001:3.1|1002:4.9 */
    private String receiversInfo;
    private boolean accountStart;
    private boolean accountFinish;
    private boolean tranSuccess;
    private String respCode;
    private Date acctProcTime;
    private Date insertTime;
    private Date updateTime;
    private int version;
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

    public boolean isAccountStart() {
        return accountStart;
    }

    public void setAccountStart(boolean accountStart) {
        this.accountStart = accountStart;
    }

    public boolean isAccountFinish() {
        return accountFinish;
    }

    public void setAccountFinish(boolean accountFinish) {
        this.accountFinish = accountFinish;
    }

    public boolean isTranSuccess() {
        return tranSuccess;
    }

    public void setTranSuccess(boolean tranSuccess) {
        this.tranSuccess = tranSuccess;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public Date getAcctProcTime() {
        return acctProcTime;
    }

    public void setAcctProcTime(Date acctProcTime) {
        this.acctProcTime = acctProcTime;
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
        builder.append("TransInfo [id=");
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
        builder.append(", accountStart=");
        builder.append(accountStart);
        builder.append(", accountFinish=");
        builder.append(accountFinish);
        builder.append(", tranSuccess=");
        builder.append(tranSuccess);
        builder.append(", respCode=");
        builder.append(respCode);
        builder.append(", acctProcTime=");
        builder.append(acctProcTime);
        builder.append(", insertTime=");
        builder.append(insertTime);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append(", version=");
        builder.append(version);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }

}
