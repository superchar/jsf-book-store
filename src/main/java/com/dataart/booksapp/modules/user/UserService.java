package com.dataart.booksapp.modules.user;

import com.dataart.booksapp.modules.book.BookViewModel;
import com.dataart.booksapp.modules.general.NotExistsException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@Local
public interface UserService {

    boolean areCredentialsValid(UserCredentials userCredentials);

    UserViewModel createNew(User user) throws NotExistsException;

    void addBookToUserFavorites(BookViewModel bookViewModel,UserViewModel userViewModel) throws NotExistsException;

    List<BookViewModel> getFavoriteBooks(int first, int quantity, UserViewModel userViewModel) throws NotExistsException;

    long getFavoriteBooksCount(UserViewModel userViewModel) throws NotExistsException;

    UserViewModel findByEmail(UserViewModel userViewModel);
}
