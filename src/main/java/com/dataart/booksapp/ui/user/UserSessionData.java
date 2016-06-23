package com.dataart.booksapp.ui.user;

import com.dataart.booksapp.domain.user.UserViewModel;

import javax.annotation.ManagedBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


/**
 * Created by vlobyntsev on 31.05.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class UserSessionData implements Serializable {

    private UserViewModel currentUser = new UserViewModel();

    private boolean isAuthenticated;

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public UserViewModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserViewModel currentUser) {
        this.currentUser = currentUser;
    }
}
