package com.dataart.booksapp.domain.book;

import com.dataart.booksapp.domain.author.AuthorModelMapper;
import com.dataart.booksapp.domain.genre.GenreModelMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public class BookModelMapper {

    public static Book mapFromView(BookViewModel bookViewModel) {
        Book book = new Book();
        book.setIdBook(bookViewModel.getIdBook());
        book.setDescription(bookViewModel.getDescription());
        book.setIsbn(bookViewModel.getIsbn());
        book.setTitle(bookViewModel.getTitle());
        return book;
    }

    public static BookViewModel mapFromDomain(Book book) {
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setIdBook(book.getIdBook());
        bookViewModel.setDescription(book.getDescription());
        bookViewModel.setIsbn(book.getIsbn());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthors(AuthorModelMapper.mapFromDomainModelList(book.getAuthors()));
        bookViewModel.setGenres(GenreModelMapper.mapFromDomainList(book.getGenres()));
        bookViewModel.setBookDataText(new String(book.getBookData()));
        return bookViewModel;
    }

    public static List<BookViewModel> mapFromDomainList(List<Book> books) {
        List<BookViewModel> mappedList = new ArrayList<>();
        for (Book book : books) {
            mappedList.add(mapFromDomain(book));
        }
        return mappedList;
    }
}
