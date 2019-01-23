package com.haoweima.utils.validate.validator;

import com.haoweima.utils.validate.common.CheckResult;
import com.haoweima.utils.validate.common.ResultEnum;
import com.haoweima.utils.validate.exception.FieldValidateException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 传送带
 * Created by haoweima on 2019/1/21.
 */
public final class CheckHander {

    public static CheckResult hander(Object obj) throws Exception {
        CheckResult result;
        Class<?> clz = obj.getClass();

        //获取受检对象类型注解，检查是否绑定检验规则
        Annotation[] annotations = clz.getAnnotations();
        String location = null;
        for (Annotation anno : annotations) {
            if (anno instanceof Calibrator) {
                location = ((Calibrator) anno).location();
                break;
            }
        }
        if (location == null) {
            throw new FieldValidateException("受检对象所属类中无@Calibrator注释");
        }

        //检查注解是否实现了Validator接口
        Class<?> validator = Thread.currentThread().getContextClassLoader().loadClass(location);
        Class<?>[] interfaces = validator.getInterfaces();
        boolean isImpl = false;
        for (Class<?> temp : interfaces) {
            if (temp.getName().equals(Validator.class.getName())) {
                isImpl = true;
                break;
            }
        }
        if (!isImpl) {
            throw new FieldValidateException("对象绑定的检验规则Enum没有继承Validator接口");
        }

        Validator[] checkRules = (Validator[]) validator.getEnumConstants();
        Inspector<Validator> runner = new Inspector<>(checkRules, obj);
        result = runner.docheck();

        return result;
    }

    /**
     * 一个执行器
     * 感觉像是安检器里的X光
     * @param <T>
     */
    private static class Inspector<T extends Validator> {

        private T[] checkRules;
        private Object object;

        Inspector(T[] checkRules, Object object) {
            this.checkRules = checkRules;
            this.object = object;
        }

        CheckResult docheck() throws Exception {
            Class clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();
            CheckResult result = new CheckResult();

            if (checkRules.length != fields.length) {
                throw new FieldValidateException("检查条例和实体类数量不匹配");
            }

            for (int i = 0; i < checkRules.length; i++) {
                String fieldName = fields[i].getName();
                Method method = clz.getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                result = checkRules[i].doValidate(method.invoke(object));

                if (result.getResultCode() == ResultEnum.FAIL) break;
            }

            return result;
        }
    }
}
