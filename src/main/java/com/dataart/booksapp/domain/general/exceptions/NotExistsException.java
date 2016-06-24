package com.dataart.booksapp.domain.general.exceptions;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public class NotExistsException extends RuntimeException {

    private static final String PROPERTY_MESSAGE = "%s with property %s = %s does not exist";
    private static final String ENTITY_MESSAGE = "%s does not exist";
    private static final String MESSAGE = "Object does not exist";

    public NotExistsException(String entity,String property,Object value){
        super(String.format(PROPERTY_MESSAGE,entity,property,value));
    }

    public NotExistsException(String entityName){
        super(String.format(ENTITY_MESSAGE,entityName));
    }

    public NotExistsException(){
        super(MESSAGE);
    }
}
