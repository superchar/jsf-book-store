package com.dataart.booksapp.ui.user.profile;

import com.dataart.booksapp.domain.book.BookService;
import com.dataart.booksapp.domain.book.BookViewModel;
import com.dataart.booksapp.ui.general.LazyDataModelBase;
import com.dataart.booksapp.ui.user.UserSessionData;

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
    private UserSessionData userSessionData;

    @EJB
    private BookService bookService;

    @Override
    protected int getRowsQuantity() {
        return (int)bookService.getCountByCreator(userSessionData.getCurrentUser());
    }

    @Override
    protected List<BookViewModel> getEntitiesFor(int first, int quantity) {
        return bookService.findBooksByCreator(first,quantity, userSessionData.getCurrentUser());
    }

    @Override
    protected int getIdFromEntity(BookViewModel entity) {
        return entity.getIdBook();
    }
}
