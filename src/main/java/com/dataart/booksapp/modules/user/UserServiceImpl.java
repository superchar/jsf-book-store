package com.dataart.booksapp.modules.user;

import com.dataart.booksapp.modules.book.Book;
import com.dataart.booksapp.modules.book.BookModelMapper;
import com.dataart.booksapp.modules.book.BookRepository;
import com.dataart.booksapp.modules.book.BookViewModel;
import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.general.Preconditions;

import javax.ejb.EJB;
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

    public UserViewModel createNew(User user) throws NotExistsException {
        if (doesUserExist(user)) {
            throw new NotExistsException("User", "email", user.getEmail());
        }
        return UserModelMapper.mapFromDomain(userRepository.createNew(user));
    }

    @Override
    public void addBookToUserFavorites(BookViewModel bookViewModel, UserViewModel userViewModel) throws NotExistsException {
        Preconditions.throwNotExistsIfNull(bookViewModel, userViewModel);
        Preconditions.throwIllegalArgumentIfFalse(bookViewModel.getIdBook() > 0, "Invalid book id");
        Preconditions.throwIllegalArgumentIfFalse(userViewModel.getEmail() != null, "Empty user email");
        Book book = bookRepository.findById(bookViewModel.getIdBook());
        Preconditions.throwNotExistsIfNull(book);
        User user = userRepository.findByEmail(userViewModel.getEmail());
        Preconditions.throwNotExistsIfNull(user);
        if (!userRepository.isBookInFavoriteList(user, book)) {
            user.getBooks().add(book);
            userRepository.edit(user);
        }
    }

    @Override
    public List<BookViewModel> getFavoriteBooks(int first, int quantity, UserViewModel userViewModel) throws NotExistsException {
        User user = loadUserFromUserViewModel(userViewModel);
        return BookModelMapper.mapFromDomainList(userRepository.getFavoriteBooks(first, quantity, user));
    }

    @Override
    public UserViewModel findByEmail(UserViewModel userViewModel) {
        return UserModelMapper.mapFromDomain(userRepository.findByEmail(userViewModel.getEmail()));
    }

    @Override
    public long getFavoriteBooksCount(UserViewModel userViewModel) throws NotExistsException {
        User user = loadUserFromUserViewModel(userViewModel);
        return userRepository.getFavoriteBooksCount(user);
    }

    private User loadUserFromUserViewModel(UserViewModel userViewModel) throws NotExistsException {
        Preconditions.throwIllegalArgumentIfParamIsNull(userViewModel);
        Preconditions.throwIllegalArgumentIfFalse(userViewModel.getEmail() != null, "User email is empty");
        User user = userRepository.findByEmail(userViewModel.getEmail());
        Preconditions.throwNotExistsIfNull(user);
        return user;
    }

    private boolean doesUserExist(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        return existingUser != null;
    }
}
