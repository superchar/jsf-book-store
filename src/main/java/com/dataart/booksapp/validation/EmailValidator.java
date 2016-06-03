package com.dataart.booksapp.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@FacesValidator("emailValidator")
public class EmailValidator extends AbstractUIInputValidator {

    private static final String EMAIL_PATTERN = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        String email = (String) value;
        if (!email.matches(EMAIL_PATTERN)) {
            failValidation("Email is not valid", uiComponent, facesContext);
        }
    }
}
