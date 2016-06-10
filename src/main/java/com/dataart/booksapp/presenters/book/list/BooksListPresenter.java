package com.dataart.booksapp.presenters.book.list;

import com.dataart.booksapp.routing.Routes;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 06.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class BooksListPresenter implements Serializable {

    @Inject
    private BookLazyDataModel bookLazyDataModel;

    public BookLazyDataModel getBookLazyDataModel() {
        return bookLazyDataModel;
    }

    public Routes editBook(){
        return Routes.editing;
    }
}
