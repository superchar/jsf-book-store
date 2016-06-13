package com.dataart.booksapp.presenters.book;

import com.dataart.booksapp.modules.author.Author;
import com.dataart.booksapp.modules.author.AuthorViewModel;
import com.dataart.booksapp.modules.book.BookViewModel;
import com.dataart.booksapp.modules.genre.Genre;
import com.dataart.booksapp.modules.genre.GenreViewModel;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class BookData implements Serializable {

    private BookViewModel currentSelectedBook = new BookViewModel();

    public BookViewModel getCurrentSelectedBook() {
        return currentSelectedBook;
    }

    public void setCurrentSelectedBook(BookViewModel currentSelectedBook) {
        this.currentSelectedBook = currentSelectedBook;
    }
}
