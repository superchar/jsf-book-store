package com.dataart.booksapp.domain.book;

import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
import com.dataart.booksapp.domain.user.UserViewModel;

import javax.ejb.Local;
import java.io.IOException;
import java.util.List;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
@Local
public interface BookService {

    void addNew(BookViewModel bookViewModel,UserViewModel currentUserViewModel) throws NotExistsException,IOException;

    BookViewModel edit(BookViewModel bookViewModel) throws NotExistsException,IOException;

    void remove(BookViewModel bookViewModel, UserViewModel currentUser) throws NotExistsException;

    List<BookViewModel> getBooksInRange(int from, int resultsQuantity);

    BookViewModel findById(int id);

    long getBooksCount();

    long getCountByCreator(UserViewModel creatorViewModel);

    List<BookViewModel> findBooksByCreator(int first,int quantity,UserViewModel creatorViewModel);

}
