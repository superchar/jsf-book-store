package com.dataart.booksapp.domain.book;

import com.dataart.booksapp.domain.author.Author;
import com.dataart.booksapp.domain.author.AuthorModelMapper;
import com.dataart.booksapp.domain.author.AuthorRepository;
import com.dataart.booksapp.domain.author.AuthorViewModel;
import com.dataart.booksapp.domain.general.GeneralMapper;
import com.dataart.booksapp.domain.general.Preconditions;
import com.dataart.booksapp.domain.general.exceptions.PermissionDeniedException;
import com.dataart.booksapp.domain.genre.*;
import com.dataart.booksapp.domain.user.User;
import com.dataart.booksapp.domain.user.UserModelMapper;
import com.dataart.booksapp.domain.user.UserRepository;
import com.dataart.booksapp.domain.user.UserViewModel;
import org.apache.commons.io.IOUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
@Stateless
public class BookServiceImpl implements BookService {

    @Inject
    private GenreRepository genreRepository;

    @Inject
    private AuthorRepository authorRepository;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private UserRepository userRepository;

    public List<BookViewModel> getInRange(int from, int resultsQuantity) {
        Preconditions.throwIllegalArgumentIfNegativeValue(from,resultsQuantity);
        return BookModelMapper.mapFromDomainList(bookRepository.getInRange(from, resultsQuantity));
    }

    @Override
    public void add(BookViewModel bookViewModel, UserViewModel currentUserViewModel)throws IOException {
        throwIllegalArgumentIfBookIsNull(bookViewModel);
        throwIllegalArgumentIfUserIsNull(currentUserViewModel);
        Book newBook = buildNewBookFromViewModel(bookViewModel);
        User currentUser = loadUserFromViewModel(currentUserViewModel);
        newBook.setCreator(currentUser);
        bookRepository.add(newBook);
    }

    @Override
    public void remove(BookViewModel bookViewModel, UserViewModel currentUserViewModel) {
        throwIllegalArgumentIfBookIsNull(bookViewModel);
        throwIllegalArgumentIfUserIsNull(currentUserViewModel);
        User currentUser = loadUserFromViewModel(currentUserViewModel);
        Book removingBook = loadBookFromViewModel(bookViewModel);
        if (removingBook.getCreator().getIdUser() != currentUser.getIdUser()) {
            throw new PermissionDeniedException("User cant delete book not created by him");
        }
        bookRepository.remove(removingBook);
    }

    public long getCount() {
        return bookRepository.getCount();
    }

    @Override
    public BookViewModel edit(BookViewModel bookViewModel) throws IOException {
        throwIllegalArgumentIfBookIsNull(bookViewModel);
        Book editingBook = loadBookFromViewModel(bookViewModel);
        if (wasBookChangedAfterUpdating(editingBook, bookViewModel)) {
            editingBook = updateBook(editingBook, bookViewModel);
            bookRepository.edit(editingBook);
        }
        return BookModelMapper.mapFromDomain(editingBook);
    }

    @Override
    public BookViewModel findById(int id) {
        return BookModelMapper.mapFromDomain(bookRepository.findById(id));
    }

    @Override
    public List<BookViewModel> findByCreator(int first, int quantity, UserViewModel creatorViewModel) {
        throwIllegalArgumentIfUserIsNull(creatorViewModel);
        return BookModelMapper.mapFromDomainList(bookRepository.findByCreator(first, quantity, UserModelMapper.mapFromView(creatorViewModel)));
    }

    @Override
    public long getCountByCreator(UserViewModel creatorViewModel) {
        throwIllegalArgumentIfUserIsNull(creatorViewModel);
        return bookRepository.getCountByCreator(UserModelMapper.mapFromView(creatorViewModel));
    }

    private boolean wasBookChangedAfterUpdating(Book oldBook, BookViewModel editedBook) {
        return !oldBook.getTitle().equals(editedBook.getTitle()) ||
                !oldBook.getIsbn().equals(editedBook.getIsbn()) ||
                !oldBook.getDescription().equals(editedBook.getDescription()) ||
                wereAuthorsChangedAfterUpdating(oldBook, editedBook) ||
                wereGenresChangedAfterUpdating(oldBook, editedBook) ||
                wasBookdataChangedAfterUpdating(editedBook);
    }

    private boolean wereAuthorsChangedAfterUpdating(Book oldBook, BookViewModel editedBook) {
        return !areCollectionsEqual(oldBook.getAuthors(),
                editedBook.getAuthors(),
                Author::getIdAuthor,
                AuthorViewModel::getAuthorId);
    }

    private boolean wereGenresChangedAfterUpdating(Book oldBook, BookViewModel editedBook) {
        return !areCollectionsEqual(oldBook.getGenres(),
                editedBook.getGenres(),
                Genre::getIdGenre,
                GenreViewModel::getIdGenre);
    }

    private boolean wasBookdataChangedAfterUpdating(BookViewModel bookViewModel) {
        return bookViewModel.getBookDataPart() != null && bookViewModel.getBookDataPart().getSize() > 0;
    }

    private <TFirst, TSecond, TProperty> boolean areCollectionsEqual(Collection<TFirst> firstCollection,
                                                                     Collection<TSecond> secondCollection,
                                                                     Function<TFirst, TProperty> firstCollectionPropertySelector,
                                                                     Function<TSecond, TProperty> secondCollectionPropertySelector) {
        return firstCollection
                .stream()
                .allMatch(f ->
                        secondCollection.stream()
                                .anyMatch(s ->
                                        firstCollectionPropertySelector.apply(f)
                                                .equals(secondCollectionPropertySelector.apply(s))));
    }

    private Book updateBook(Book oldBook, BookViewModel editedBook) throws IOException {
        oldBook.setTitle(editedBook.getTitle());
        oldBook.setIsbn(editedBook.getIsbn());
        oldBook.setDescription(editedBook.getDescription());
        oldBook.setGenres(loadGenresFromViewModel(editedBook));
        oldBook.setAuthors(loadAuthorsFromViewModel(editedBook));
        if (wasBookdataChangedAfterUpdating(editedBook)) {
            oldBook = mapBookDataPartToByteArray(oldBook, editedBook);
        }
        return oldBook;
    }

    private Book buildNewBookFromViewModel(BookViewModel bookViewModel) throws IOException {
        Book newBook = BookModelMapper.mapFromView(bookViewModel);
        newBook.getGenres().addAll(loadGenresFromViewModel(bookViewModel));
        newBook.getAuthors().addAll(loadAuthorsFromViewModel(bookViewModel));
        newBook = mapBookDataPartToByteArray(newBook, bookViewModel);
        return newBook;
    }

    private Book mapBookDataPartToByteArray(Book book, BookViewModel bookViewModel) throws IOException {
        byte[] array = IOUtils.toByteArray(bookViewModel.getBookDataPart().getInputStream());
        book.setBookData(array);
        return book;
    }

    private Book loadBookFromViewModel(BookViewModel bookViewModel) {
        return GeneralMapper.loadModelFromViewModel(bookViewModel, BookViewModel::getIdBook, bookRepository::findById);
    }

    private User loadUserFromViewModel(UserViewModel userViewModel)  {
        return GeneralMapper.loadModelFromViewModel(userViewModel, UserViewModel::getIdUser, userRepository::findById);
    }

    private void throwIllegalArgumentIfBookIsNull(BookViewModel bookViewModel){
        Preconditions.throwIllegalArgumentIfParamIsNull(bookViewModel,"bookViewModel");//.Net nameof would be suitable here..
    }

    private void throwIllegalArgumentIfUserIsNull(UserViewModel userViewModel){
        Preconditions.throwIllegalArgumentIfParamIsNull(userViewModel,"userViewModel");
    }

    private List<Genre> loadGenresFromViewModel(BookViewModel bookViewModel){
        List<Genre> genres = genreRepository.findByIds(GenreModelMapper.mapViewListToIds(bookViewModel.getGenres()));
        Preconditions.throwEmptyCollectionIfEmpty(genres,"Genres pagination is empty");
        return genres;
    }

    private List<Author> loadAuthorsFromViewModel(BookViewModel bookViewModel){
        List<Author> authors = authorRepository.findByIds(AuthorModelMapper.mapViewListToIds(bookViewModel.getAuthors()));
        Preconditions.throwEmptyCollectionIfEmpty(authors,"Authors pagination is empty");
        return authors;
    }
}
