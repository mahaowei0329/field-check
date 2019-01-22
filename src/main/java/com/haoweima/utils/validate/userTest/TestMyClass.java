package com.haoweima.utils.validate.userTest;

import com.haoweima.utils.validate.validator.CheckHander;
import com.haoweima.utils.validate.validator.CheckResult;

/**
 * Created by haoweima on 2019/1/21.
 */
public class TestMyClass {

    public static void main(String[] args) {
        Bean bean = new Bean();
        bean.setVar2(20);
        bean.setVar3("hello world");
        bean.setVar1(bean);

        try {
            CheckResult checkResult = CheckHander.hander(bean);
            System.out.println(checkResult.getResultCode().getCode() + " " + checkResult.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
