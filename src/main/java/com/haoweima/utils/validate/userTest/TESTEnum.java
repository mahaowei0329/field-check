package com.haoweima.utils.validate.userTest;

import com.haoweima.utils.validate.validator.Calibrator;
import com.haoweima.utils.validate.validator.Validator;

/**
 * Created by haoweima on 2019/1/21.
 */

@Calibrator(location = "null")
public enum TESTEnum implements Validator {

    Var1("var1验证成功", "var1验证失败") {
        @Override
        public String doValidate(Object obj) {
            if(obj == null)
                return this.failMsg;
            else
                return this.successMsg;
        }
    },
    Var2("var2验证成功", "var2验证失败") {
        @Override
        public String doValidate(Object obj) {
            if(obj instanceof Integer){
                return (Integer)obj > 10 ? this.failMsg : this.successMsg;
            }
            else return this.failMsg;
        }
    },
    Var3("var3验证成功", "var3验证失败") {
        @Override
        public String doValidate(Object obj) {
            if(obj instanceof String){
                if (((String) obj).startsWith("hello")){
                    return this.successMsg;
                }
                else return this.failMsg;
            }
            else return this.failMsg;
        }
    };

    TESTEnum(String successMsg, String failMsg) {
        this.successMsg = successMsg;
        this.failMsg = failMsg;
    }

    String successMsg;
    String failMsg;
}
