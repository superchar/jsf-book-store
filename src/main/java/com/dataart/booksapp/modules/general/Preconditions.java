package com.dataart.booksapp.modules.general;

import java.util.Collection;
import java.util.List;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public class Preconditions {//Stolen from google.collections

    public static void throwNotExistsIfNull(Object object) throws NotExistsException{
        throwExceptionIfNull(object,new NotExistsException());
    }

    public static void throwIllegalArgumentIfParamIsNull(Object object){
        throwExceptionIfNull(object,new IllegalArgumentException("Argument does not present"));
    }

    public static void throwNotExistsIfEmpty(Collection collection) throws NotExistsException{
        if(collection==null || collection.size()==0){
            throw new NotExistsException();
        }
    }

    private static<T extends Exception> void throwExceptionIfNull(Object object,T exception) throws T{
        if(object==null){
            throw exception;
        }
    }

}
