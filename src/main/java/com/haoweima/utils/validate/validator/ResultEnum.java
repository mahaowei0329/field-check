package com.haoweima.utils.validate.validator;

/**
 * Created by haoweima on 2019/1/22.
 */
public enum ResultEnum {

    SUCCESS("0000"),
    FAIL("9999");

    ResultEnum(String code) {
        this.code = code;
    }

    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
