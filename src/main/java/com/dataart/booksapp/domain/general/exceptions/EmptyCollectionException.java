package com.dataart.booksapp.domain.general.exceptions;

/**
 * Created by Андрей on 23.06.2016.
 */
public class EmptyCollectionException extends RuntimeException {

    public EmptyCollectionException(String message){
        super(message);
    }

}
