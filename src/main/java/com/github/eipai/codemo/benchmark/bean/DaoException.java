package com.github.eipai.codemo.benchmark.bean;

public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private RespCode respCode = RespCode.DATA_ACCESS_EXCEPTION;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(RespCode respCode) {
        super();
        this.respCode = respCode;
    }

    public DaoException(RespCode respCode, String message) {
        super(message);
        this.respCode = respCode;
    }

    public RespCode getRespCode() {
        return respCode;
    }

    public void setRespCode(RespCode respCode) {
        this.respCode = respCode;
    }

}
