package com.dataart.booksapp.domain.book;

import com.dataart.booksapp.domain.author.Author;
import com.dataart.booksapp.domain.author.AuthorModelMapper;
import com.dataart.booksapp.domain.author.AuthorRepository;
import com.dataart.booksapp.domain.author.AuthorViewModel;
import com.dataart.booksapp.domain.general.GeneralMapper;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
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
        return BookModelMapper.mapFromDomainList(bookRepository.getInRange(from, resultsQuantity));
    }

    @Override
    public void add(BookViewModel bookViewModel, UserViewModel currentUserViewModel)throws IOException {
        throwIllegalArgumentIfBookOrUserIsNull(bookViewModel,currentUserViewModel);
        List<Genre> genres = genreRepository.findByIds(GenreModelMapper.mapViewListToIds(bookViewModel.getGenres()));
        Preconditions.throwEmptyCollectionIfEmpty(genres,"Genres list is empty");
        List<Author> authors = authorRepository.findByIds(AuthorModelMapper.mapViewListToIds(bookViewModel.getAuthors()));
        Preconditions.throwEmptyCollectionIfEmpty(authors,"Authors list is empty");
        User currentUser = loadUserFromViewModel(currentUserViewModel);
        Book newBook = buildNewBook(bookViewModel, genres, authors);
        newBook.setCreator(currentUser);
        bookRepository.add(newBook);
    }

    @Override
    public void remove(BookViewModel bookViewModel, UserViewModel currentUserViewModel) throws PermissionDeniedException {
        throwIllegalArgumentIfBookOrUserIsNull(bookViewModel,currentUserViewModel);
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
        return BookModelMapper.mapFromDomainList(bookRepository.findByCreator(first, quantity, UserModelMapper.mapFromView(creatorViewModel)));
    }

    @Override
    public long getCountByCreator(UserViewModel creatorViewModel) {
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
        return !this.areCollectionsEqual(oldBook.getAuthors(),
                editedBook.getAuthors(),
                Author::getIdAuthor,
                AuthorViewModel::getAuthorId);
    }

    private boolean wasBookdataChangedAfterUpdating(BookViewModel bookViewModel) {
        return bookViewModel.getBookDataPart() != null && bookViewModel.getBookDataPart().getSize() > 0;
    }

    private boolean wereGenresChangedAfterUpdating(Book oldBook, BookViewModel editedBook) {
        return this.areCollectionsEqual(oldBook.getGenres(),
                editedBook.getGenres(),
                Genre::getIdGenre,
                GenreViewModel::getIdGenre);
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
        oldBook.setGenres(genreRepository.findByIds(GenreModelMapper.mapViewListToIds(editedBook.getGenres())));
        oldBook.setAuthors(authorRepository.findByIds(AuthorModelMapper.mapViewListToIds(editedBook.getAuthors())));
        if (wasBookdataChangedAfterUpdating(editedBook)) {
            oldBook = mapBookDataPartToByteArray(oldBook, editedBook);
        }
        return oldBook;
    }

    private Book buildNewBook(BookViewModel bookViewModel, List<Genre> genres, List<Author> authors) throws IOException {
        Book newBook = BookModelMapper.mapFromView(bookViewModel);
        newBook.getGenres().addAll(genres);
        newBook.getAuthors().addAll(authors);
        newBook = mapBookDataPartToByteArray(newBook, bookViewModel);
        return newBook;
    }

    private Book mapBookDataPartToByteArray(Book book, BookViewModel bookViewModel) throws IOException {
        byte[] array = IOUtils.toByteArray(bookViewModel.getBookDataPart().getInputStream());
        book.setBookData(array);
        return book;
    }

    private Book loadBookFromViewModel(BookViewModel bookViewModel) throws NotExistsException {
        return GeneralMapper.loadModelFromViewModel(bookViewModel, BookViewModel::getIdBook, bookRepository::findById);
    }

    private User loadUserFromViewModel(UserViewModel userViewModel) throws NotExistsException {
        return GeneralMapper.loadModelFromViewModel(userViewModel, UserViewModel::getIdUser, userRepository::findById);
    }

    private void throwIllegalArgumentIfBookOrUserIsNull(BookViewModel bookViewModel,UserViewModel userViewModel){
        Preconditions.throwIllegalArgumentIfParamIsNull(bookViewModel,"bookViewModel");//.Net nameof would be suitable here..
        Preconditions.throwIllegalArgumentIfParamIsNull(userViewModel,"userViewModel");
    }
}
