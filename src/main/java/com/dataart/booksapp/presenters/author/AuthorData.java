package com.dataart.booksapp.presenters.author;

import com.dataart.booksapp.modules.author.AuthorViewModel;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 14.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class AuthorData implements Serializable {

    private AuthorViewModel currentSelectedAuthor;

    public AuthorViewModel getCurrentSelectedAuthor() {
        return currentSelectedAuthor;
    }

    public void setCurrentSelectedAuthor(AuthorViewModel currentSelectedAuthor) {
        this.currentSelectedAuthor = currentSelectedAuthor;
    }
}
