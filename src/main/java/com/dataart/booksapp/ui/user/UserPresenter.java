package com.dataart.booksapp.ui.user;

import com.dataart.booksapp.domain.general.exceptions.ExistsWithException;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
import com.dataart.booksapp.domain.user.User;
import com.dataart.booksapp.domain.user.UserCredentials;
import com.dataart.booksapp.domain.user.UserService;
import com.dataart.booksapp.ui.book.data.BookLocalData;
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
 * Created by vlobyntsev on 31.05.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class UserPresenter implements Serializable {

    @EJB
    private UserService userService;

    @Inject
    private Router router;

    @Inject
    private BookLocalData bookLocalData;

    @Inject
    private UserSessionData userSessionData;

    public Routes login() {
        if (userService.areCredentialsValid(new UserCredentials(userSessionData.getCurrentUser().getEmail(), userSessionData.getCurrentUser().getPassword()))) {
            userSessionData.setCurrentUser(userService.findByEmail(userSessionData.getCurrentUser()));
            userSessionData.setAuthenticated(true);
            return Routes.booksList;
        }
        MessageHelper.createGlobalMessage("Login or password is not correct");
        return null;
    }

    public Routes logout(){
        userSessionData.setAuthenticated(false);
        return router.moveToAuthentication();
    }

    public Routes passRegistration() {
        try {
            userService.add(userSessionData.getCurrentUser());
        } catch (ExistsWithException ex) {
            MessageHelper.createGlobalMessage(ex.getMessage());
            return null;
        }
        MessageHelper.createGlobalMessage("Registration was successful");
        return null;
    }

    public Routes addToBookFavorites() {
        try {
            userService.addBookToFavorites(bookLocalData.getCurrentEntity(), userSessionData.getCurrentUser());
        } catch (NotExistsException e) {
            MessageHelper.createGlobalMessage("Adding book to favorite was failed because on not existing of the of the entities");
            return null;
        }
        return router.moveToMyProfile();
    }


}
