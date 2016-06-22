package com.dataart.booksapp.validation.genre;

import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.genre.GenreService;
import com.dataart.booksapp.presenters.genre.list.GenreData;
import com.dataart.booksapp.validation.general.AbstractValidator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 * Created by vlobyntsev on 22.06.2016.
 */
@FacesValidator("genreValidator")
public class GenreValidator extends AbstractValidator {

    @Inject
    private GenreData genreData;

    @Inject
    private GenreService genreService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        try {
            if (!genreService.canEditGenre(genreData.getCurrentSelectedGenre())) {
                failValidation("Genre with such name already exists", uiComponent, facesContext);
            }
        } catch (NotExistsException ex) {
            failValidation("Genre does not exist", uiComponent, facesContext);
        }
    }
}
