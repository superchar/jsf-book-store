package com.dataart.booksapp.modules.book;

import com.dataart.booksapp.modules.user.User;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
@ManagedBean
@ApplicationScoped
public class BookRepository {

    @PersistenceContext(unitName = "books")
    private EntityManager entityManager;

    public Book addNewBook(Book book) {
        entityManager.persist(book);
        return book;
    }

    public Book findById(int id){
        return entityManager.find(Book.class,id);
    }

    public Book editBook(Book book){
        return entityManager.merge(book);
    }

    public long getBooksCount(){
        return entityManager.createNamedQuery("book.count",Long.class)
                .getSingleResult();
    }

    public Book remove(Book book){
        entityManager.remove(book);
        return book;
    }

    public List<Book> getBooksInRange(int from, int resultsQuantity) {
        return entityManager.createNamedQuery("book.findAll", Book.class)
                .setFirstResult(from)
                .setMaxResults(resultsQuantity)
                .getResultList();
    }

    public List<Book> findByCreator(int first,int quantity,User creator){
        return entityManager.createNamedQuery("book.findByCreator",Book.class)
                .setParameter("creatorId",creator.getIdUser())
                .setFirstResult(first)
                .setMaxResults(quantity)
                .getResultList();
    }

    public long getCountByCreator(User creator){
        return entityManager.createNamedQuery("book.getCountByCreator",Long.class)
                .setParameter("creatorId",creator.getIdUser())
                .getSingleResult();
    }
}
