package com.dataart.booksapp.modules.user;

import org.omg.CORBA.portable.ApplicationException;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
public class UserExistsException extends Exception {

    private static final String MESSAGE = "User %s already exists";

    public UserExistsException(String userName){
        super(String.format(MESSAGE,userName));
    }
}
