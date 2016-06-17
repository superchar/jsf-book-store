package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.author.Author;
import com.dataart.booksapp.modules.genre.Genre;
import com.dataart.booksapp.modules.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name = "book.findAll",query = "select b from Book as b"),
        @NamedQuery(name = "book.findByCreator",query = "select b from Book as b where b.creator.idUser=:creatorId"),
        @NamedQuery(name = "book.getCountByCreator",query = "select count(b) from Book as b where b.creator.idUser=:creatorId"),
        @NamedQuery(name = "book.count",query = "select count(b) from Book as b")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBook;

    private String title;

    private String isbn;

    private String description;

    @Lob
    private byte [] bookData;

    @ManyToOne
    @JoinColumn(name = "creatorId")
    private User creator;

    @ManyToMany
    @JoinTable(name = "books_genres",
    joinColumns = @JoinColumn(name = "bookId"),
    inverseJoinColumns = @JoinColumn(name = "genreId"))
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "books_authors",
    joinColumns = @JoinColumn(name = "bookId"),
    inverseJoinColumns = @JoinColumn(name = "authorId"))
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    private List<User> users = new ArrayList<>();

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

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public byte[] getBookData() {
        return bookData;
    }

    public void setBookData(byte[] bookData) {
        this.bookData = bookData;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
