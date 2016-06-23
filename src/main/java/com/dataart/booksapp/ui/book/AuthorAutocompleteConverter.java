package com.dataart.booksapp.ui.book;

import com.dataart.booksapp.domain.author.AuthorService;
import com.dataart.booksapp.domain.author.AuthorViewModel;
import com.dataart.booksapp.ui.general.AbstractAutocompleteConverter;

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
public class AuthorAutocompleteConverter extends AbstractAutocompleteConverter {

    @Inject
    private AuthorService authorService;

    @Override
    protected Object getObjectFromId(int id) {
        return authorService.findById(id);
    }

    @Override
    protected String getStringFromObject(Object value) {
        return Integer.toString(((AuthorViewModel)value).getAuthorId());
    }
}
