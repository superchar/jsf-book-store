package com.dataart.booksapp.ui.general.data;

/**
 * Created by vlobyntsev on 23.06.2016.
 */
public abstract class AbstractDataBean<T> {

    private T currentEntity;

    public AbstractDataBean(){}

    public AbstractDataBean(T initialValue){
        this.currentEntity = initialValue;
    }

    public T getCurrentEntity() {
        return currentEntity;
    }

    public void setCurrentEntity(T currentEntity) {
        this.currentEntity = currentEntity;
    }
}
