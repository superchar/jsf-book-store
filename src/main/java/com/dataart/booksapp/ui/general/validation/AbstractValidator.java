package com.dataart.booksapp.ui.general.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
public abstract class AbstractValidator implements Validator {

    public abstract void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException;

    protected void failValidation(String errorMessage,UIComponent uiComponent,FacesContext facesContext){
        facesContext.addMessage(uiComponent.getClientId(facesContext), new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,null));
    }
}
