package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.author.Author;
import com.dataart.booksapp.modules.author.AuthorModelMapper;
import com.dataart.booksapp.modules.author.AuthorRepository;
import com.dataart.booksapp.modules.author.AuthorViewModel;
import com.dataart.booksapp.modules.general.GeneralMapper;
import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.general.Preconditions;
import com.dataart.booksapp.modules.genre.*;
import com.dataart.booksapp.modules.user.User;
import com.dataart.booksapp.modules.user.UserModelMapper;
import com.dataart.booksapp.modules.user.UserRepository;
import com.dataart.booksapp.modules.user.UserViewModel;
import org.apache.commons.io.IOUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static com.dataart.booksapp.routing.Routes.editingBook;

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

    public List<BookViewModel> getBooksInRange(int from, int resultsQuantity) {
        return BookModelMapper.mapFromDomainList(bookRepository.getBooksInRange(from, resultsQuantity));
    }

    @Override
    public void addNew(BookViewModel bookViewModel, UserViewModel currentUserViewModel) throws NotExistsException, IOException {
        List<Genre> genres = genreRepository.findByIds(GenreModelMapper.mapViewListToIds(bookViewModel.getGenres()));
        Preconditions.throwNotExistsIfEmpty(genres);
        List<Author> authors = authorRepository.findByIds(AuthorModelMapper.mapViewListToIds(bookViewModel.getAuthors()));
        Preconditions.throwNotExistsIfEmpty(authors);
        User currentUser = loadUserFromViewModel(currentUserViewModel);
        Book newBook = buildNewBook(bookViewModel, genres, authors);
        newBook.setCreator(currentUser);
        bookRepository.addNewBook(newBook);
    }

    @Override
    public void remove(BookViewModel bookViewModel, UserViewModel currentUserViewModel) throws NotExistsException {
        User currentUser = loadUserFromViewModel(currentUserViewModel);
        Book removingBook = loadBookFromViewModel(bookViewModel);
        if (removingBook.getCreator().getIdUser() == currentUser.getIdUser()) {
            bookRepository.remove(removingBook);
        }
    }

    public long getBooksCount() {
        return bookRepository.getBooksCount();
    }

    @Override
    public BookViewModel edit(BookViewModel bookViewModel) throws NotExistsException, IOException {
        Book editingBook = loadBookFromViewModel(bookViewModel);
        if (wasBookChangedAfterUpdating(editingBook, bookViewModel)) {
            editingBook = updateBook(editingBook, bookViewModel);
            bookRepository.editBook(editingBook);
        }
        return BookModelMapper.mapFromDomain(editingBook);
    }

    @Override
    public BookViewModel findById(int id) {
        return BookModelMapper.mapFromDomain(bookRepository.findById(id));
    }

    @Override
    public List<BookViewModel> findBooksByCreator(int first, int quantity, UserViewModel creatorViewModel) {
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
            oldBook = mapBookDataToByteArray(oldBook, editedBook);
        }
        return oldBook;
    }

    private Book buildNewBook(BookViewModel bookViewModel, List<Genre> genres, List<Author> authors) throws IOException {
        Book newBook = BookModelMapper.mapFromView(bookViewModel);
        newBook.getGenres().addAll(genres);
        newBook.getAuthors().addAll(authors);
        newBook = mapBookDataToByteArray(newBook, bookViewModel);
        return newBook;
    }

    private Book mapBookDataToByteArray(Book book, BookViewModel bookViewModel) throws IOException {
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
}
