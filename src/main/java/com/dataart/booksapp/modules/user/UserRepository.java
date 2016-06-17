package com.dataart.booksapp.modules.user;


import com.dataart.booksapp.modules.book.Book;
import com.dataart.booksapp.modules.general.AbstractRepository;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vlobyntsev on 31.05.2016.
 */

@ManagedBean
@ApplicationScoped
public class UserRepository extends AbstractRepository implements Serializable {

    @PersistenceContext(unitName = "books")
    private EntityManager entityManager;

    public User findById(int id){
        return entityManager.find(User.class,id);
    }

    public User findByEmail(String email){
        TypedQuery<User> query = entityManager.
                createNamedQuery("user.findByEmail",User.class)
                .setParameter("email",email);
        return processSingleResult(query);
    }

    public User edit(User user){
        return entityManager.merge(user);
    }

    public List<Book> getFavoriteBooks(int startPage,int quantity,User user){
        return entityManager.createNamedQuery("user.getFavoriteBooks",Book.class)
                .setParameter("userId",user.getIdUser())
                .setFirstResult(startPage)
                .setMaxResults(quantity)
                .getResultList();
    }

    public boolean isBookInFavoriteList(User user,Book book){
        return entityManager.createNamedQuery("user.isBookInFavoriteList",Boolean.class)
                .setParameter("bookId",book.getIdBook())
                .setParameter("userId",user.getIdUser())
                .getSingleResult();
    }

    public long getFavoriteBooksCount(User user){
        return entityManager.createNamedQuery("user.getFavoriteBooksCount",Long.class)
                .setParameter("userId",user.getIdUser())
                .getSingleResult();
    }

    public User createNew(User user){
        entityManager.persist(user);
        return user;
    }
}
