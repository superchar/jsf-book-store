package com.dataart.booksapp.ui.general.validation;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
public abstract class AbstractUIInputValidator extends AbstractValidator {
    @Override
    protected void failValidation(String errorMessage, UIComponent uiComponent, FacesContext facesContext) {
        super.failValidation(errorMessage, uiComponent, facesContext);
        ((UIInput)uiComponent).setValid(false);
    }
}
