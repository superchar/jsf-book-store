package com.dataart.booksapp.ui.author;

import com.dataart.booksapp.domain.author.AuthorService;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
import com.dataart.booksapp.ui.author.data.AuthorLocalData;
import com.dataart.booksapp.ui.author.data.AuthorSessionData;
import com.dataart.booksapp.ui.general.helpers.MessageHelper;
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
public class AuthorPresenter implements Serializable {

    @EJB
    private AuthorService authorService;

    @Inject
    private Router router;

    @Inject
    private AuthorLocalData authorLocalData;

    @Inject
    private AuthorSessionData authorSessionData;


    public Routes addAuthor() {
        authorService.add(authorLocalData.getCurrentEntity());
        return Routes.authorsList;
    }

    public Routes editAuthor() {
        try {
            authorService.edit(authorSessionData.getCurrentEntity());
            return Routes.authorsList;
        } catch (NotExistsException ex) {
             MessageHelper.createGlobalMessage(ex.getMessage());
        }
        return null;
    }

    public Routes requestAuthorEditing(){
        authorSessionData.setCurrentEntity(authorLocalData.getCurrentEntity());
        return router.moveToAuthorEditing();
    }

}
