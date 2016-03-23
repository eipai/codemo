package com.github.eipai.codemo.benchmark.bean;

public class TranException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private RespCode respCode = RespCode.UNEXPECTED_EXCEPTION;

    public TranException() {
        super();
    }

    public TranException(String message) {
        super(message);
    }

    public TranException(RespCode respCode, String message) {
        super(message);
        this.respCode = respCode;
    }

    public TranException(RespCode respCode) {
        super();
        this.respCode = respCode;
    }

    public TranException(RespCode respCode, Throwable throwable) {
        super(throwable);
        this.respCode = respCode;
    }

    public RespCode getRespCode() {
        return respCode;
    }

    public void setRespCode(RespCode respCode) {
        this.respCode = respCode;
    }

}
