package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.author.Author;
import com.dataart.booksapp.modules.genre.Genre;
import com.dataart.booksapp.modules.user.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBook;

    private String title;

    private String isbn;

    private String description;

    @ManyToMany
    @JoinTable(name = "books_genres",
    joinColumns = @JoinColumn(name = "bookId"),
    inverseJoinColumns = @JoinColumn(name = "genreId"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "books_authors",
    joinColumns = @JoinColumn(name = "bookId"),
    inverseJoinColumns = @JoinColumn(name = "authorId"))
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "books_users",
    joinColumns = @JoinColumn(name = "bookId"),
    inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<User> users;

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
}
