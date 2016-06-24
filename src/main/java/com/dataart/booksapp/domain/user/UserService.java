package com.dataart.booksapp.domain.user;

import com.dataart.booksapp.domain.book.BookViewModel;
import com.dataart.booksapp.domain.general.exceptions.ExistsWithException;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@Local
public interface UserService {

    boolean areCredentialsValid(UserCredentials userCredentials);

    UserViewModel add(UserViewModel userViewModel);

    void addBookToFavorites(BookViewModel bookViewModel, UserViewModel userViewModel);

    List<BookViewModel> getFavoriteBooks(int first, int quantity, UserViewModel userViewModel);

    long getFavoriteBooksCount(UserViewModel userViewModel);

    UserViewModel findByEmail(UserViewModel userViewModel);
}
