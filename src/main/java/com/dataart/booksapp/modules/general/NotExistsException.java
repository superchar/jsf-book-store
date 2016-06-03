package com.dataart.booksapp.modules.general;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public class NotExistsException extends Exception {

    private static final String PROPERTY_MESSAGE = "%s with property %s = %s does not exist";
    private static final String MESSAGE = "Object does not exist";

    public NotExistsException(String entity,String property,Object value){
        super(String.format(PROPERTY_MESSAGE,entity,property,value));
    }

    public NotExistsException(){
        super(MESSAGE);
    }
}
