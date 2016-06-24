package com.dataart.booksapp.ui.general.routing;


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

    public Routes moveToBookAdding(){
        return Routes.addingBook;
    }

    public Routes moveToBookEditing(){
        return Routes.editingBook;
    }

    public Routes moveToRegistration(){
        return Routes.registration;
    }

    public Routes moveToAuthentication(){
        return Routes.authentication;
    }

    public Routes moveToBooksList(){
        return Routes.booksList;
    }

    public Routes moveToAuthorsList(){
        return Routes.authorsList;
    }

    public Routes moveToGenresList(){
        return Routes.genresList;
    }

    public Routes moveToGenreEditing(){
        return Routes.editingGenre;
    }

    public Routes moveToGenreAdding(){
        return Routes.addingGenre;
    }

    public Routes moveToAuthorEditing(){
        return Routes.editingAuthor;
    }

    public Routes moveToAuthorAdding(){
        return Routes.addingAuthor;
    }

    public Routes moveToBookReading(){
        return Routes.readingBook;
    }

    public Routes moveToMyProfile(){
        return Routes.profile;
    }

}
