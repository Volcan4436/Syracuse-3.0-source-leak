package org.mapleir.dot4j.systems.auth;

import java.lang.reflect.Method;

public class IntegrityF {
    public static boolean hasUnexpectedCode(Class<?> clazz) {
        try {
            Method validateMethod = clazz.getDeclaredMethod("validate");
            String validateCode = validateMethod.toString();

            if (!validateCode.contains("return false;")) {
                return true;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return false;
    }
}
