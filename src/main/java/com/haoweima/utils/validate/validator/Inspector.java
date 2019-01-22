package com.haoweima.utils.validate.validator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 检察官
 * Created by haoweima on 2019/1/21.
 */
public class Inspector<T extends Validator> {

    private T[] checkRules;
    private Object object;

    public Inspector(T[] checkRules, Object object) {
        this.checkRules = checkRules;
        this.object = object;
    }

    public boolean docheck() throws Exception{
        Class clz = object.getClass();
        Field[] fields = clz.getDeclaredFields();

        if(checkRules.length != fields.length){
            return false;
        }

        for(int i = 0; i < checkRules.length; i++){
            String fieldName = fields[i].getName();
            Method method = clz.getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            System.out.println(checkRules[i].doValidate(method.invoke(object)));
        }

        return true;
    }
}
