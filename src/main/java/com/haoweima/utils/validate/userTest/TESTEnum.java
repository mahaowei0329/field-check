package com.haoweima.utils.validate.userTest;

import com.haoweima.utils.validate.validator.CheckResult;
import com.haoweima.utils.validate.validator.ResultEnum;
import com.haoweima.utils.validate.validator.Validator;

/**
 * Created by haoweima on 2019/1/21.
 */

public enum TESTEnum implements Validator{

    Var1("var1验证成功", "var1验证失败") {
        @Override
        public CheckResult doValidate(Object obj) {
            CheckResult result = new CheckResult();

            if(obj == null){
                result.setResultCode(ResultEnum.FAIL);
                result.setResultMsg(this.failMsg);
                return result;
            } else{
                result.setResultCode(ResultEnum.SUCCESS);
                result.setResultMsg(this.successMsg);
                return result;
            }
        }
    },
    Var2("var2验证成功", "var2验证失败") {
        @Override
        public CheckResult doValidate(Object obj) {
            CheckResult result = new CheckResult();

            if(obj instanceof Integer && (Integer)obj > 10){
                result.setResultCode(ResultEnum.SUCCESS);
                result.setResultMsg(this.successMsg);
                return result;
            } else {
                result.setResultCode(ResultEnum.FAIL);
                result.setResultMsg(this.failMsg);
                return result;
            }
        }
    },
    Var3("var3验证成功", "var3验证失败") {
        @Override
        public CheckResult doValidate(Object obj) {
            CheckResult result = new CheckResult();

            if(obj instanceof String && ((String) obj).startsWith("hello")) {
                result.setResultCode(ResultEnum.SUCCESS);
                result.setResultMsg(this.successMsg);
                return result;
            } else{
                result.setResultCode(ResultEnum.FAIL);
                result.setResultMsg(this.failMsg);
                return result;
            }
        }
    };

    TESTEnum(String successMsg, String failMsg) {
        this.successMsg = successMsg;
        this.failMsg = failMsg;
    }

    String successMsg;
    String failMsg;
}
