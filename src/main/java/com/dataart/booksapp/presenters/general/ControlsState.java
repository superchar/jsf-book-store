package com.dataart.booksapp.presenters.general;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 17.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class ControlsState implements Serializable  {

    private boolean generalEnabled;

    private boolean deletingEnabled;

    public boolean isGeneralEnabled() {
        return generalEnabled;
    }

    public boolean isDeletingEnabled() {
        return deletingEnabled;
    }

    public void enableGeneral(){
        generalEnabled = true;
    }

    public void disableGeneral(){
        generalEnabled = false;
    }

    public void enableDeleting(){
        deletingEnabled = true;
    }

    public void disableDeleting(){
        deletingEnabled = false;
    }

}
