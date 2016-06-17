package com.dataart.booksapp.presenters.general;

import java.util.function.Function;

/**
 * Created by vlobyntsev on 14.06.2016.
 */
public class CheckingUtil {

    public static <T> boolean wasEntitySelectedForEditing(T entity, Function<T,Integer> entityIdFetcher){
        return entity!=null && entityIdFetcher.apply(entity) > 0;
    }
}
