package com.dataart.booksapp.modules.book;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
@ManagedBean
@ApplicationScoped
public class BookRepository {

    @PersistenceContext(unitName = "books")
    private EntityManager entityManager;

    public Book addNewBook(Book book){
        entityManager.persist(book);
        return book;
    }

}
