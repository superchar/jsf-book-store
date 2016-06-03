package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.author.AuthorViewModel;
import com.dataart.booksapp.modules.genre.GenreViewModel;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public class BookViewModel {

    private int idBook;

    private String title;

    private String isbn;

    private String description;

    private AuthorViewModel author;

    private GenreViewModel genre;

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorViewModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorViewModel author) {
        this.author = author;
    }

    public GenreViewModel getGenre() {
        return genre;
    }

    public void setGenre(GenreViewModel genre) {
        this.genre = genre;
    }
}
