package com.haoweima.utils.validate.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoweima on 2019/1/21.
 */
public class CalibratorHandler {

    private static final Class annotationClz = Calibrator.class;

    public void handerAnnotation(){

    }

    public List<Class<?>> getAllClass(){
        List<Class<?>> result = new ArrayList<>();


        return result;
    }

    public static void main(String[] args) {
        CalibratorHandler calibratorHandler = new CalibratorHandler();
        calibratorHandler.handerAnnotation();
    }
}
