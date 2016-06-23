package com.dataart.booksapp.ui.book.validation;

import com.dataart.booksapp.ui.general.validation.AbstractValidator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 * Created by vlobyntsev on 22.06.2016.
 */
@FacesValidator("bookDataValidator")
public class BookDataValidator extends AbstractValidator {

    private static final long LIMITING_SIZE_BYTES = 4294967295L;
    private static final String PERMITTED_CONTENT_TYPE = "text/plain";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        Part uploadedBook = (Part) value;
        if (uploadedBook != null) {
            if (!uploadedBook.getContentType().equals(PERMITTED_CONTENT_TYPE)) {
                failValidation("Invalid content type", uiComponent, facesContext);
            } else if (uploadedBook.getSize() <= 0) {
                failValidation("Book data is empty", uiComponent, facesContext);
            } else if (uploadedBook.getSize() > LIMITING_SIZE_BYTES) {
                failValidation("Size of uploaded book is too big", uiComponent, facesContext);
            }
        }
    }
}
