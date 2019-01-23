package com.haoweima.utils.validate.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 传送带
 * Created by haoweima on 2019/1/21.
 */
public class CheckHander {

    public static CheckResult hander(Object obj) throws Exception{
        CheckResult result = null;
        Class<?> clz = obj.getClass();

        Annotation[] annotations = clz.getAnnotations();
        String location = null;
        for(Annotation anno : annotations){
            if(anno instanceof Calibrator){
                location = ((Calibrator) anno).location();
                break;
            }
        }

        if(location != null){
            Class<?> validator = Thread.currentThread().getContextClassLoader().loadClass(location);
            Class<?>[] interfaces = validator.getInterfaces();

            boolean isImpl = false;
            for(Class<?> temp : interfaces){
                if(temp.getName().equals(Validator.class.getName())){
                    isImpl = true;
                    break;
                }
            }

            if(isImpl){
                Validator[] checkRules = (Validator[])validator.getEnumConstants();
                Inspector<Validator> runner = new Inspector(checkRules, obj);
                result = runner.docheck();
            }
        }
        return result;
    }

    private static class Inspector<T extends Validator> {

        private T[] checkRules;
        private Object object;

        Inspector(T[] checkRules, Object object) {
            this.checkRules = checkRules;
            this.object = object;
        }

        CheckResult docheck() throws Exception{
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
                result = checkRules[i].doValidate(method.invoke(object));

                if(result.resultCode == ResultEnum.FAIL) break;
            }

            return result;
        }
    }
}
