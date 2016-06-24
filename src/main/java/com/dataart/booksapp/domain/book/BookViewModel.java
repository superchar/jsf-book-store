package com.dataart.booksapp.domain.book;

import com.dataart.booksapp.domain.author.AuthorViewModel;
import com.dataart.booksapp.domain.genre.GenreViewModel;

import javax.servlet.http.Part;
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

    private Part bookDataPart;

    private String bookDataText;

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

    public Part getBookDataPart() {
        return bookDataPart;
    }

    public void setBookDataPart(Part bookDataPart) {
        this.bookDataPart = bookDataPart;
    }

    public String getBookDataText() {
        return bookDataText;
    }

    public void setBookDataText(String bookDataText) {
        this.bookDataText = bookDataText;
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

    @Override
    public boolean equals(Object other) {
        if (other instanceof BookViewModel) {
            BookViewModel model = (BookViewModel) other;
            return this.getIdBook() == model.getIdBook();
        }
        return false;
    }
}
