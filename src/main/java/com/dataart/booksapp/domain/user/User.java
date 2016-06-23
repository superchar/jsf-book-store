package com.dataart.booksapp.domain.user;

import com.dataart.booksapp.domain.book.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@Table(name = "user")
@Entity
@NamedQueries({
        @NamedQuery(name = "user.findByEmail",query = "select u from User as u where u.email=:email"),
        @NamedQuery(name = "user.isBookInFavoriteList",query = "select count(u) > 0 from User as u join u.books as b where b.idBook=:bookId and u.idUser=:userId"),
        @NamedQuery(name = "user.getFavoriteBooks",query = "select b from User as u join u.books as b where u.idUser=:userId"),
        @NamedQuery(name = "user.getFavoriteBooksCount",query = "select count(b) from User as u join u.books as b where u.idUser=:userId")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @ManyToMany
    @JoinTable(name = "books_users",
            inverseJoinColumns = @JoinColumn(name = "bookId"),
            joinColumns = @JoinColumn(name = "userId"))
    private List<Book> books = new ArrayList<>();

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Book> getBooks() {
        return books;
    }
}
