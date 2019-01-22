package com.haoweima.utils.validate.userTest;

import com.haoweima.utils.validate.validator.Inspector;

/**
 * Created by haoweima on 2019/1/21.
 */
public class TestMyClass {

    public static void main(String[] args) {
        Bean bean = new Bean();
        bean.setVar2(2);
        bean.setVar3("hello world");
        bean.setVar1(bean);

        Inspector<TESTEnum> runner = new Inspector<>(TESTEnum.values(), bean);

        try {
            runner.docheck();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
