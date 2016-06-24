package com.dataart.booksapp.ui.general.helpers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by vlobyntsev on 24.06.2016.
 */
public class MessageHelper {

    public static void createGlobalMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

}
