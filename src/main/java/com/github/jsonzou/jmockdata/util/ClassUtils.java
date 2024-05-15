package com.github.jsonzou.jmockdata.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author joker-pper 2024年05月14日 下午19:49:16
 */
public class ClassUtils {

    private ClassUtils() {

    }


    /**
     * 获取继承类的实际类型列表
     *
     * @param clazz
     * @return
     */
    public static Type[] getSuperclassGenericActualTypeArguments(Class<?> clazz) {
        Type[] typeParameters = clazz.getTypeParameters();
        int typeParametersLength = typeParameters.length;
        if (typeParametersLength > 0) {
            throw new RuntimeException(String.format("class %s has typeParameters, can't get superclass generic actual type arguments", clazz.getName()));
        }

        Type superclassType = clazz.getGenericSuperclass();
        if (superclassType == null || superclassType != null && superclassType == Object.class) {
            Type[] genericInterfaces = clazz.getGenericInterfaces();
            if (genericInterfaces != null && genericInterfaces.length == 1) {
                superclassType = genericInterfaces[0];
            }
        }

        ParameterizedType parameterizedType = null;

        while (superclassType != null && parameterizedType == null) {
            if (superclassType instanceof Class) {
                superclassType = ((Class) superclassType).getGenericSuperclass();
            } else if (superclassType instanceof ParameterizedType) {
                parameterizedType = (ParameterizedType) superclassType;
                superclassType = null;
            }
        }

        if (parameterizedType != null) {
            return parameterizedType.getActualTypeArguments();
        }

        return null;
    }

    /**
     * 通过class获取对应的实例对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> clazz) {
        try {
            return (T) clazz.getConstructor().newInstance();
        } catch (Exception ignored) {
        }
        try {
            return (T) clazz.newInstance();
        } catch (Exception ignored) {
        }
        throw new RuntimeException(String.format("newInstance error, cause by class: %s", clazz.getName()));
    }


}
