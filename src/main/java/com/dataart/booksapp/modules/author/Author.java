package com.dataart.booksapp.modules.author;

import com.dataart.booksapp.modules.book.Book;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@Entity
@Table(name = "author")
@NamedQueries({
        @NamedQuery(name = "author.findAll",query = "select a from Author as a"),
        @NamedQuery(name = "author.findByNamePrefix",query = "select a from Author as a where a.firstName like :namePrefix or a.lastName like :namePrefix"),
        @NamedQuery(name = "author.findByIds",query = "select a from Author as a where a.idAuthor in :authorsIds")
})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAuthor;

    private String firstName;

    private String lastName;

    private String authorInfo;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }
}
