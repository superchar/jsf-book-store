package com.dataart.booksapp.domain.genre;

import com.dataart.booksapp.domain.book.Book;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@Entity
@Table(name = "genre")
@NamedQueries({
        @NamedQuery(name = "genre.findByPrefix",query = "select g from Genre as g where g.name like :namePrefix"),
        @NamedQuery(name = "genre.findByIds",query = "select g from Genre as g where g.idGenre in :genresIds"),
        @NamedQuery(name = "genre.findAll",query = "select g from Genre as g"),
        @NamedQuery(name = "genre.getCount",query = "select count(g) from Genre as g"),
        @NamedQuery(name = "genre.getCountForName",query = "select count(g) from Genre as g where g.name=:name")
})
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenre;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Book> books;

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
