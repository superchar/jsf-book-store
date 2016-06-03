package com.dataart.booksapp.presenters.user;

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
public class UserData implements Serializable {

    private String email;

    private String password;

    private String lastName;

    private String firstName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
