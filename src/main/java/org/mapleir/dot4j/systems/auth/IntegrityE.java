package org.mapleir.dot4j.systems.auth;

import java.lang.reflect.Method;

public class IntegrityE {
    public static boolean checkMethodExistence() {
        Class<?> clazz = API.class;
        String methodName = "validate";
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            return true;
        } catch (NoSuchMethodException e) {

        }
        return false;
    }
}
