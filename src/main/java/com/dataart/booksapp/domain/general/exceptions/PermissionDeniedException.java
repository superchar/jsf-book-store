package com.dataart.booksapp.domain.general.exceptions;

/**
 * Created by Андрей on 23.06.2016.
 */
public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException(String message){
        super(message);
    }

}
