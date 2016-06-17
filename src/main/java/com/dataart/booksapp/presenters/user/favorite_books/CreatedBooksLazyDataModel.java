package com.dataart.booksapp.presenters.user.favorite_books;

import com.dataart.booksapp.modules.book.BookService;
import com.dataart.booksapp.modules.book.BookViewModel;
import com.dataart.booksapp.presenters.general.LazyDataModelBase;
import com.dataart.booksapp.presenters.user.UserData;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vlobyntsev on 17.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class CreatedBooksLazyDataModel extends LazyDataModelBase<BookViewModel> implements Serializable {

    @Inject
    private UserData userData;

    @EJB
    private BookService bookService;

    @Override
    protected int getRowsQuantity() {
        return (int)bookService.getCountByCreator(userData.getCurrentUser());
    }

    @Override
    protected List<BookViewModel> getEntitiesFor(int first, int quantity) {
        return bookService.findBooksByCreator(first,quantity,userData.getCurrentUser());
    }

    @Override
    protected int getIdFromEntity(BookViewModel entity) {
        return entity.getIdBook();
    }
}
