package com.dataart.booksapp.domain.general;

import com.dataart.booksapp.domain.general.exceptions.EmptyCollectionException;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;

import java.util.Collection;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public class Preconditions {//Stolen from google.collections

    public static void throwIllegalArgumentIfFalse(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void throwEmptyCollectionIfEmpty(Collection collection,String message) throws EmptyCollectionException {
        if (collection.size() == 0) {
            throw new EmptyCollectionException(message);
        }
    }

    public static void throwNotExistsIfNull(Object... objects) throws NotExistsException {
        for (Object object : objects) {
            throwExceptionIfNull(object, new NotExistsException());
        }
    }

    public static void throwIllegalArgumentIfParamIsNull(Object object, String parameterName) {
        throwExceptionIfNull(object, new IllegalArgumentException(String.format("Parameter %s does not present", parameterName)));
    }

    public static void throwNotExistsIfEmpty(Collection collection) throws NotExistsException {
        if (collection == null || collection.size() == 0) {
            throw new NotExistsException();
        }
    }

    private static <T extends Exception> void throwExceptionIfNull(Object object, T exception) throws T {
        if (object == null) {
            throw exception;
        }
    }
}
