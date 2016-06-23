package com.dataart.booksapp.ui.user;

import com.dataart.booksapp.domain.general.exceptions.ExistsException;
import com.dataart.booksapp.domain.user.User;
import com.dataart.booksapp.domain.user.UserCredentials;
import com.dataart.booksapp.domain.user.UserService;
import com.dataart.booksapp.ui.general.AbstractPresenter;
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
public class UserPresenter extends AbstractPresenter implements Serializable {

    @EJB
    private UserService userService;

    @Inject
    private Router router;

    @Inject
    private UserSessionData userSessionData;


    public Routes login() {
        if (userService.areCredentialsValid(new UserCredentials(userSessionData.getCurrentUser().getEmail(), userSessionData.getCurrentUser().getPassword()))) {
            userSessionData.setCurrentUser(userService.findByEmail(userSessionData.getCurrentUser()));
            userSessionData.setAuthenticated(true);
            return Routes.booksList;
        }
        createGlobalMessage("Login or password is not correct");
        return null;
    }

    public Routes logout(){
        userSessionData.setAuthenticated(false);
        return router.moveToAuthentication();
    }

    public Routes passRegistration() {
        try {
            userService.createNew(buildUserFromUserData());
        } catch (ExistsException ex) {
            createGlobalMessage(ex.getMessage());
            return null;
        }
        createGlobalMessage("Registration was successful");
        return null;
    }

    private User buildUserFromUserData(){
        User user = new User();
        user.setEmail(userSessionData.getCurrentUser().getEmail());
        user.setPassword(userSessionData.getCurrentUser().getPassword());
        user.setFirstName(userSessionData.getCurrentUser().getFirstName());
        user.setLastName(userSessionData.getCurrentUser().getLastName());
        return user;
    }


}
