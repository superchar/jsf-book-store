package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.general.NotExistsException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
@Local
public interface BookService {

    void addNew(BookViewModel bookViewModel) throws NotExistsException;

    void edit(BookViewModel bookViewModel) throws NotExistsException;

    List<BookViewModel> getBooksInRange(int from, int resultsQuantity);

    BookViewModel findById(int id);

    long getBooksCount();
}
