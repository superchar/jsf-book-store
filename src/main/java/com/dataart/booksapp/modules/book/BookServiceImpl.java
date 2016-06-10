package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.author.Author;
import com.dataart.booksapp.modules.author.AuthorRepository;
import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.general.Preconditions;
import com.dataart.booksapp.modules.genre.Genre;
import com.dataart.booksapp.modules.genre.GenreRepository;
import com.dataart.booksapp.modules.genre.GenreService;

import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

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

    public List<BookViewModel> getBooksInRange(int from,int resultsQuantity){
        return BookModelMapper.mapFromDomainList(bookRepository.getBooksInRange(from,resultsQuantity));
    }

    public void addNew(BookViewModel bookViewModel) throws NotExistsException {
        Genre genre = genreRepository.findById(bookViewModel.getGenres().get(0).getIdGenre());
        Preconditions.throwNotExistsIfNull(genre);
        Author author = authorRepository.findById(bookViewModel.getAuthors().get(0).getAuthorId());
        Preconditions.throwNotExistsIfNull(author);
        Book newBook = buildNewBook(bookViewModel, genre, author);
        bookRepository.addNewBook(newBook);
    }

    public long getBooksCount(){
        return bookRepository.getBooksCount();
    }

    private Book buildNewBook(BookViewModel bookViewModel, Genre genre, Author author) {
        Book newBook = BookModelMapper.mapFromView(bookViewModel);
        newBook.getGenres().add(genre);
        newBook.getAuthors().add(author);
        return newBook;
    }
}
