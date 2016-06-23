package com.dataart.booksapp.domain.general.exceptions;

/**
 * Created by vlobyntsev on 23.06.2016.
 */
public class ExistsException extends Exception {

    private static final String DEFAULT_MESSAGE = "%s with %s %s already exists";

    public ExistsException(String entityName,String propertyName, Object propertyValue) {
        super(String.format(DEFAULT_MESSAGE,entityName, propertyName, propertyValue));
    }
}
