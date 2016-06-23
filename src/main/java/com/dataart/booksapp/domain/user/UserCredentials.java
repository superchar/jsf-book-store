package com.dataart.booksapp.domain.user;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
public class UserCredentials {

    public UserCredentials(String userName, String password) {
        this.email = userName;
        this.password = password;
    }

    private String email;
    private String password;

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
}
