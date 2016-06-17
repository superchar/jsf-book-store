package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.user.User;
import com.dataart.booksapp.modules.user.UserViewModel;

import javax.ejb.Local;
import java.io.IOException;
import java.util.List;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
@Local
public interface BookService {

    void addNew(BookViewModel bookViewModel,UserViewModel currentUserViewModel) throws NotExistsException,IOException;

    void edit(BookViewModel bookViewModel) throws NotExistsException;

    void remove(BookViewModel bookViewModel, UserViewModel currentUser) throws NotExistsException;

    List<BookViewModel> getBooksInRange(int from, int resultsQuantity);

    BookViewModel findById(int id);

    long getBooksCount();

    long getCountByCreator(UserViewModel creatorViewModel);

    List<BookViewModel> findBooksByCreator(int first,int quantity,UserViewModel creatorViewModel);

}
