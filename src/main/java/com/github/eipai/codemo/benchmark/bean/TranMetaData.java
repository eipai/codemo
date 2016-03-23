package com.github.eipai.codemo.benchmark.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.eipai.codemo.benchmark.domain.AccountDetail;
import com.github.eipai.codemo.benchmark.domain.UserAccount;
import com.github.eipai.codemo.benchmark.utils.Global;

public class TranMetaData {
    private List<UserAccount> userAccounts = new ArrayList<>();
    private Map<Long, BigDecimal> userAcctDiff = new HashMap<>();
    private Map<String, List<AccountDetail>> tranDetails = new HashMap<>();
    private Map<String, Long> orderId2TransInfoIds = new HashMap<>();

    public void addAcctDetail(String orderId, long fromAcct, long toAcct, BigDecimal amount) {
        List<AccountDetail> details = tranDetails.get(orderId);
        if (null == details) {
            details = new ArrayList<>();
            tranDetails.put(orderId, details);
        }
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setAcctId(fromAcct);
        accountDetail.setAmount(amount);
        accountDetail.setCdType(CdType.DR);
        accountDetail.setRelatedAcctId(toAcct);
        details.add(accountDetail);
        accountDetail = new AccountDetail();
        accountDetail.setAcctId(toAcct);
        accountDetail.setAmount(amount);
        accountDetail.setCdType(CdType.CR);
        accountDetail.setRelatedAcctId(toAcct);
        accountDetail.setRelatedAcctId(fromAcct);
        details.add(accountDetail);
    }

    public boolean checkDrCrEqual() {
        BigDecimal sum = new BigDecimal("0");
        for (Map.Entry<Long, BigDecimal> entry : userAcctDiff.entrySet()) {
            BigDecimal value = entry.getValue();
            sum = sum.add(value);
        }
        if (!Global.Utils.equalsZero(sum)) return false;
        return true;
    }

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public Map<Long, BigDecimal> getUserAcctDiff() {
        return userAcctDiff;
    }

    public void setUserAcctDiff(Map<Long, BigDecimal> userAcctDiff) {
        this.userAcctDiff = userAcctDiff;
    }

    public Map<String, List<AccountDetail>> getTranDetails() {
        return tranDetails;
    }

    public void setTranDetails(Map<String, List<AccountDetail>> tranDetails) {
        this.tranDetails = tranDetails;
    }

    public Map<String, Long> getOrderId2TransInfoIds() {
        return orderId2TransInfoIds;
    }

    public void putOrderId2TransInfoIds(String orderId, Long transInfoId) {
        this.orderId2TransInfoIds.put(orderId, transInfoId);
    }

    public void setOrderId2TransInfoIds(Map<String, Long> orderId2TransInfoIds) {
        this.orderId2TransInfoIds = orderId2TransInfoIds;
    }
}