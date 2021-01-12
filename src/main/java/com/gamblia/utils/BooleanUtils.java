package com.gamblia.utils;

public class BooleanUtils {

    public static boolean integerToBoolean(Integer value) {
        return value != 0;
    }

    public static Integer booleanToInteger(Boolean bool) {
        return bool ? 1 : 0;
    }

}
