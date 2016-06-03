package com.dataart.booksapp.modules.user;

import com.dataart.booksapp.modules.book.Book;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@Table(name = "user")
@Entity
@NamedQueries({
        @NamedQuery(name = "user.findByEmail",query = "select u from User as u where u.email=:email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @ManyToMany(mappedBy = "users")
    private List<Book> books;

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
}
