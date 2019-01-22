package com.haoweima.utils.validate.validator;

/**
 * 安检器（检查规则
 * Created by haoweima on 2019/1/21.
 */
public interface Validator {
    CheckResult<?> doValidate(Object obj);
}
