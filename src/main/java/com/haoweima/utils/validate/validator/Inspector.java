package com.haoweima.utils.validate.validator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 校验器
 * Created by haoweima on 2019/1/21.
 */
public class Inspector<T extends Validator> {

    private T[] checkRules;
    private Object object;

    public Inspector(T[] checkRules, Object object) {
        this.checkRules = checkRules;
        this.object = object;
    }

    public CheckResult docheck() throws Exception{
        Class clz = object.getClass();
        Field[] fields = clz.getDeclaredFields();
        CheckResult result = new CheckResult();

        if(checkRules.length != fields.length){
            result = new CheckResult();
            result.setResultCode(ResultEnum.FAIL);
            result.setResultMsg("检查条例和实体类数量不匹配！");
            return result;
        }

        for(int i = 0; i < checkRules.length; i++){
            String fieldName = fields[i].getName();
            Method method = clz.getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            result = (CheckResult)checkRules[i].doValidate(method.invoke(object));

            if(result.resultCode == ResultEnum.FAIL) break;
        }

        return result;
    }
}
