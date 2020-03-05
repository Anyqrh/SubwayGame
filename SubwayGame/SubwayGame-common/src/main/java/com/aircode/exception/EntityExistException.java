package com.aircode.exception;

import org.springframework.util.StringUtils;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }
    public EntityExistException(String val) {
        super(EntityExistException.generateMessage(val));
    }


    private static String generateMessage(String val) {
        return val ;
    }
    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }
}