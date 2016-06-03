package com.dataart.booksapp.modules.user;


import com.dataart.booksapp.modules.general.AbstractRepository;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

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

    public User createNew(User user){
        entityManager.persist(user);
        return user;
    }
}
