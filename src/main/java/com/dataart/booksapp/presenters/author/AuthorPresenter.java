package com.dataart.booksapp.presenters.author;

import com.dataart.booksapp.modules.author.AuthorService;
import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.presenters.general.AbstractPresenter;
import com.dataart.booksapp.routing.Routes;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 14.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class AuthorPresenter extends AbstractPresenter implements Serializable {

    @EJB
    private AuthorService authorService;

    @Inject
    private AuthorData authorData;

    public Routes addAuthor() {
        authorService.addAuthor(authorData.getCurrentSelectedAuthor());
        return Routes.authorsList;
    }

    public Routes editAuthor() {
        try {
            authorService.editAuthor(authorData.getCurrentSelectedAuthor());
            return Routes.authorsList;
        } catch (NotExistsException ex) {
            createGlobalMessage(ex.getMessage());
        }
        return null;
    }

}
