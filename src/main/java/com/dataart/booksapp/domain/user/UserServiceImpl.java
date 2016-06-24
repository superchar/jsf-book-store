package com.dataart.booksapp.domain.user;

import com.dataart.booksapp.domain.book.Book;
import com.dataart.booksapp.domain.book.BookModelMapper;
import com.dataart.booksapp.domain.book.BookRepository;
import com.dataart.booksapp.domain.book.BookViewModel;
import com.dataart.booksapp.domain.general.exceptions.ExistsWithException;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
import com.dataart.booksapp.domain.general.Preconditions;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private BookRepository bookRepository;

    public boolean areCredentialsValid(UserCredentials userCredentials) {
        User targetUser = userRepository.findByEmail(userCredentials.getEmail());
        return targetUser != null && targetUser.getPassword().equals(userCredentials.getPassword());
    }

    @Override
    public UserViewModel add(UserViewModel userViewModel) throws ExistsWithException {
        throwIllegalArgumentIfUserIsNull(userViewModel);
        if (userRepository.doesExistWithEmail(userViewModel.getEmail())) {
            throw new ExistsWithException("User", "email", userViewModel.getEmail());
        }
        User createdUser = userRepository.add(UserModelMapper.mapFromView(userViewModel));
        return UserModelMapper.mapFromDomain(createdUser);
    }

    @Override
    public void addBookToFavorites(BookViewModel bookViewModel, UserViewModel userViewModel) {
        Preconditions.throwIllegalArgumentIfParamIsNull(bookViewModel, "bookViewModel");
        throwIllegalArgumentIfUserIsNull(userViewModel);
        Book book = bookRepository.findById(bookViewModel.getIdBook());
        Preconditions.throwNotExistsIfNull(book, "Book");
        User user = userRepository.findByEmail(userViewModel.getEmail());
        Preconditions.throwNotExistsIfNull(user, "User");
        if (!userRepository.isBookInFavoriteList(user, book)) {
            user.getBooks().add(book);
            userRepository.edit(user);
        }
    }

    @Override
    public List<BookViewModel> getFavoriteBooks(int first, int quantity, UserViewModel userViewModel) {
        Preconditions.throwIllegalArgumentIfNegativeValue(first, quantity);
        throwIllegalArgumentIfUserIsNull(userViewModel);
        User user = loadUserFromUserViewModel(userViewModel);
        return BookModelMapper.mapFromDomainList(userRepository.getFavoriteBooks(first, quantity, user));
    }

    @Override
    public UserViewModel findByEmail(UserViewModel userViewModel) {
        throwIllegalArgumentIfUserIsNull(userViewModel);
        return UserModelMapper.mapFromDomain(userRepository.findByEmail(userViewModel.getEmail()));
    }

    @Override
    public long getFavoriteBooksCount(UserViewModel userViewModel) throws NotExistsException {
        throwIllegalArgumentIfUserIsNull(userViewModel);
        User user = loadUserFromUserViewModel(userViewModel);
        return userRepository.getFavoriteBooksCount(user);
    }

    private User loadUserFromUserViewModel(UserViewModel userViewModel) throws NotExistsException {
        throwIllegalArgumentIfUserIsNull(userViewModel);
        Preconditions.throwIllegalArgumentIfFalse(userViewModel.getEmail() != null, "User email is empty");
        User user = userRepository.findByEmail(userViewModel.getEmail());
        Preconditions.throwNotExistsIfNull(user, "User");
        return user;
    }

    private void throwIllegalArgumentIfUserIsNull(UserViewModel userViewModel) {
        Preconditions.throwIllegalArgumentIfParamIsNull(userViewModel, "userViewModel");
    }
}
