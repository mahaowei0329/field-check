package com.haoweima.utils.validate.validator;

import java.lang.annotation.Annotation;

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
}
