package com.dataart.booksapp.ui.general;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public abstract class AbstractPresenter {
    
    protected void createGlobalMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
}
