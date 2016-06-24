package com.dataart.booksapp.ui.genre.converters;

import com.dataart.booksapp.domain.genre.GenreService;
import com.dataart.booksapp.domain.genre.GenreViewModel;
import com.dataart.booksapp.ui.general.autocomplete.AbstractAutocompleteConverterBase;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by vlobyntsev on 02.06.2016.
 */
@ManagedBean
@ApplicationScoped
@Named
public class GenreAutocompleteConverter extends AbstractAutocompleteConverterBase {

    @EJB
    private GenreService genreService;

    @Override
    protected Object getObjectFromId(int id) {
        return genreService.findById(id);
    }

    @Override
    protected String getStringFromObject(Object value) {
        return Integer.toString(((GenreViewModel)value).getIdGenre());
    }
}
