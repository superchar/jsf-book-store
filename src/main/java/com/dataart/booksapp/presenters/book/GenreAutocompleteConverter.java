package com.dataart.booksapp.presenters.book;

import com.dataart.booksapp.modules.genre.GenreService;
import com.dataart.booksapp.modules.genre.GenreViewModel;
import com.dataart.booksapp.presenters.general.AbstractAutocompleteConverter;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by vlobyntsev on 02.06.2016.
 */
@ManagedBean
@ApplicationScoped
@Named
public class GenreAutocompleteConverter extends AbstractAutocompleteConverter {

    @Inject
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
