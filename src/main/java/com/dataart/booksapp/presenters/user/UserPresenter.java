package com.dataart.booksapp.presenters.user;

import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.user.User;
import com.dataart.booksapp.modules.user.UserCredentials;
import com.dataart.booksapp.modules.user.UserService;
import com.dataart.booksapp.presenters.general.AbstractPresenter;
import com.dataart.booksapp.routing.Routes;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class UserPresenter extends AbstractPresenter implements Serializable {

    @EJB
    private UserService userService;

    @Inject
    private UserData userData;

    public Routes login() {
        if (userService.areCredentialsValid(new UserCredentials(userData.getCurrentUser().getEmail(), userData.getCurrentUser().getPassword()))) {
            userData.setCurrentUser(userService.findByEmail(userData.getCurrentUser()));
            userData.setAuthenticated(true);
            return Routes.booksList;
        }
        createGlobalMessage("Login or password is not correct");
        return null;
    }

    public Routes passRegistration() {
        try {
            userService.createNew(buildUserFromUserData());
        } catch (NotExistsException ex) {
            createGlobalMessage(ex.getMessage());
            return null;
        }
        return Routes.booksList;
    }

    private User buildUserFromUserData(){
        User user = new User();
        user.setEmail(userData.getCurrentUser().getEmail());
        user.setPassword(userData.getCurrentUser().getPassword());
        user.setFirstName(userData.getCurrentUser().getFirstName());
        user.setLastName(userData.getCurrentUser().getLastName());
        return user;
    }


}
