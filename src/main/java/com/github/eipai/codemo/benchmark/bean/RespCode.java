package com.github.eipai.codemo.benchmark.bean;

public enum RespCode {

    SUCCESS("00", "success"),

    NULL_ARGUMENT("10", "argument cannot be null"),
    WRONG_AMOUNT("11", "amount cannot be empty or smaller than 0"),
    NO_RECEIVER("12", "trans order without any receiver"),
    USER_ACCOUNT_NOT_EXIST("13", "UserAccount not found"),
    USER_ACCOUNT_LOCKED("14", "UserAccount has been locked"),
    TRAN_RECEIVER_ARG_WRONG("15", "trans order receiver(s) value is wrong"),
    TRAN_RECEIVERS_WRONG("16", "trans order receivers parse error"),
    TRAN_RECEIVERS_EMPTY("17", "trans order receivers is empty"),
    TRAN_RECEIVER_ID_WRONG("18", "trans order receivers id parse error"),
    TRAN_RECEIVER_AMOUNT_WRONG("19", "trans order receivers amount parse error"),
    TRAN_RECEIVERS_AMOUNT_WRONG("20", "trans order receivers sum amount not equals to the order amount"),
    ACCOUNT_BALANCE_INSUFFICIENT("21", "UserAccount balance insufficient"),
    ORDER_ID_EMPTY("22", "orderId is empty"),
    ORDER_ID_DUPLICATE("23", "orderId is duplicate"),
    CR_DR_NOT_EQUALS("24", "trans order CR & DR not equals"),

    NETWORK_EXCEPTION("70", "network exception"),

    DATA_ACCESS_EXCEPTION("80", "data access exception"),
    OPTIMISTIC_LOCK("81", "optimistic lock exception"),

    UNEXPECTED_EXCEPTION("91", "unexpected exception"),
    FAILURE("99", "failure");

    private String code;
    private String message;

    private RespCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
