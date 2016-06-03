package com.dataart.booksapp.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@FacesValidator("passwordValidator")
public class PasswordValidator extends AbstractUIInputValidator {

    /*(?=.*)*/
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\.!@#\\$%\\^&\\*\\(\\)\\-_=\\+]).+$";

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        String password = (String) value;
        if (!password.matches(PASSWORD_PATTERN)) {
            failValidation("Password is not valid", uiComponent, facesContext);
        }
    }
}
