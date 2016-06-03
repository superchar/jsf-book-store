package com.dataart.booksapp.routing;


import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@ManagedBean
@ApplicationScoped
@Named
public class Router {

    public Routes moveToAuthorization(){
        return Routes.authorization;
    }

    public Routes moveToRegistration(){
        return Routes.registration;
    }
}
