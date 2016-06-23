package com.dataart.booksapp.domain.general;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
public abstract class AbstractRepository {

    protected <T>T processSingleResult(TypedQuery<T> query){
        try{
            return query.getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }
}
