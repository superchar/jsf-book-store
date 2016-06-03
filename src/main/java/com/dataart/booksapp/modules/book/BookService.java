package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.general.NotExistsException;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public interface BookService {
    void addNew(BookViewModel bookViewModel) throws NotExistsException;
}
