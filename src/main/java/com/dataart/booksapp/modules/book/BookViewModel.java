package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.author.AuthorViewModel;
import com.dataart.booksapp.modules.genre.GenreViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public class BookViewModel {

    private int idBook;

    private String title;

    private String isbn;

    private String description;

    private List<AuthorViewModel> authors = new ArrayList<>();

    private List<GenreViewModel> genres = new ArrayList<>();

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

    public List<AuthorViewModel> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorViewModel> authors) {
        this.authors = authors;
    }

    public List<GenreViewModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreViewModel> genres) {
        this.genres = genres;
    }
}
