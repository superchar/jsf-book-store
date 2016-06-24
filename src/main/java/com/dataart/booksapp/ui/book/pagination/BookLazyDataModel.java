package com.dataart.booksapp.ui.book.pagination;

import com.dataart.booksapp.domain.book.BookService;
import com.dataart.booksapp.domain.book.BookViewModel;
import com.dataart.booksapp.ui.general.pagination.AbstractLazyDataModelBase;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vlobyntsev on 06.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class BookLazyDataModel extends AbstractLazyDataModelBase<BookViewModel> implements Serializable {

    @EJB
    private BookService bookService;

    @Override
    protected int getRowsQuantity() {
        return (int)bookService.getCount();
    }

    @Override
    protected List<BookViewModel> getEntitiesFor(int first, int quantity) {
        return bookService.getInRange(first,quantity);
    }

    @Override
    protected int getIdFromEntity(BookViewModel entity) {
        return entity.getIdBook();
    }
}
