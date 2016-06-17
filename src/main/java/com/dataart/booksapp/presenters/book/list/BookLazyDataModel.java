package com.dataart.booksapp.presenters.book.list;

import com.dataart.booksapp.modules.book.Book;
import com.dataart.booksapp.modules.book.BookService;
import com.dataart.booksapp.modules.book.BookViewModel;
import com.dataart.booksapp.presenters.general.LazyDataModelBase;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by vlobyntsev on 06.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class BookLazyDataModel extends LazyDataModelBase<BookViewModel> implements Serializable {

    @EJB
    private BookService bookService;

    @Override
    protected int getRowsQuantity() {
        return (int)bookService.getBooksCount();
    }

    @Override
    protected List<BookViewModel> getEntitiesFor(int first, int quantity) {
        return bookService.getBooksInRange(first,quantity);
    }

    @Override
    protected int getIdFromEntity(BookViewModel entity) {
        return entity.getIdBook();
    }
}
