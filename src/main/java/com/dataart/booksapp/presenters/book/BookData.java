package com.dataart.booksapp.presenters.book;

import com.dataart.booksapp.modules.author.Author;
import com.dataart.booksapp.modules.author.AuthorViewModel;
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

    private AuthorViewModel author;

    private GenreViewModel genre;

    private String title;

    private String isbn;

    private String description;

    public AuthorViewModel getAuthor() {
        return author;
    }

    public GenreViewModel getGenre() {
        return genre;
    }

    public void setGenre(GenreViewModel genre) {
        this.genre = genre;
    }

    public void setAuthor(AuthorViewModel author) {
        this.author = author;
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
}
