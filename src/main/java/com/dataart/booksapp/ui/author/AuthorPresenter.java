package com.dataart.booksapp.ui.author;

import com.dataart.booksapp.domain.author.AuthorService;
import com.dataart.booksapp.domain.author.AuthorViewModel;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
import com.dataart.booksapp.ui.author.data.AuthorLocalData;
import com.dataart.booksapp.ui.author.data.AuthorSessionData;
import com.dataart.booksapp.ui.general.AbstractPresenter;
import com.dataart.booksapp.ui.general.CheckingUtil;
import com.dataart.booksapp.ui.general.routing.Router;
import com.dataart.booksapp.ui.general.routing.Routes;

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
    private AuthorSessionData authorSessionData;

    @Inject
    private AuthorLocalData authorLocalData;

    @Inject
    private Router router;

    public Routes addAuthor() {
        authorService.addAuthor(authorLocalData.getCurrentEntity());
        return Routes.authorsList;
    }

    public Routes editAuthor() {
        try {
            authorService.editAuthor(authorSessionData.getCurrentEntity());
            return Routes.authorsList;
        } catch (NotExistsException ex) {
            createGlobalMessage(ex.getMessage());
        }
        return null;
    }

    public Routes requestAuthorEditing(){
        authorSessionData.setCurrentEntity(authorLocalData.getCurrentEntity());
        return router.moveToAuthorEditing();
    }

}
