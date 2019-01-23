package com.haoweima.utils.validate.common;

/**
 * Created by haoweima on 2019/1/22.
 */
public class CheckResult<T> {

    private ResultEnum resultCode;
    private String resultMsg;

    private T result;

    public ResultEnum getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultEnum resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
