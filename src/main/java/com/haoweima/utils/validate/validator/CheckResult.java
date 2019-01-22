package com.haoweima.utils.validate.validator;

/**
 * Created by haoweima on 2019/1/22.
 */
public class CheckResult<T> {

    ResultEnum resultCode;
    String resultMsg;

    T result;

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
