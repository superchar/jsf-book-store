package com.dataart.booksapp.ui.book.validation;

import com.dataart.booksapp.ui.general.validation.AbstractValidator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 * Created by vlobyntsev on 13.06.2016.
 */
@FacesValidator("isbnValidator")
public class BookIsbnValidator extends AbstractValidator {

    private static final String CORRECT_ISBN_PATTERN = "^[0-9]+$";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        String isbn = (String) value;
        if (!doesIsbnHavePermittedLength(isbn) || !isbn.matches(CORRECT_ISBN_PATTERN)) {
            failValidation("Isbn is not correct", uiComponent, facesContext);
        }
    }

    private boolean doesIsbnHavePermittedLength(String isbn) {
        return isbn.length() == 10 || isbn.length() == 13;
    }
}
