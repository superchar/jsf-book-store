package com.dataart.booksapp.modules.general;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public class Preconditions {//Stolen from google.collections

    public static void throwNotExistsIfNull(Object object) throws NotExistsException{
        if(object==null){
            throwNotExistsException();
        }
    }

    private static void throwNotExistsException() throws NotExistsException{
        throw new NotExistsException();
    }
}
