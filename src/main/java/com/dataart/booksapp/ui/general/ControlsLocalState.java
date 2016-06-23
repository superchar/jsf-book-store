package com.dataart.booksapp.ui.general;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 17.06.2016.
 */
@ManagedBean
@ViewScoped
@Named
public class ControlsLocalState implements Serializable  {

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

    public void enableAll(){
        enableDeleting();
        enableGeneral();
    }
}
