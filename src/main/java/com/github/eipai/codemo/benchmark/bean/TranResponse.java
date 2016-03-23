package com.github.eipai.codemo.benchmark.bean;

import java.io.Serializable;

public class TranResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private RespCode respCode;
    private String detailMessage;

    public TranResponse() {
        super();
    }

    public TranResponse(String id, RespCode respCode) {
        super();
        this.id = id;
        this.respCode = respCode;
    }

    public TranResponse(String id, RespCode respCode, String detailMessage) {
        super();
        this.id = id;
        this.respCode = respCode;
        this.detailMessage = detailMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RespCode getRespCode() {
        return respCode;
    }

    public void setRespCode(RespCode respCode) {
        this.respCode = respCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TranResponse [id=");
        builder.append(id);
        builder.append(", respCode=");
        builder.append(respCode);
        builder.append(", detailMessage=");
        builder.append(detailMessage);
        builder.append("]");
        return builder.toString();
    }

}
