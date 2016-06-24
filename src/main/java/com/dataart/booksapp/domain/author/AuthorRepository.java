package com.dataart.booksapp.domain.author;


import com.dataart.booksapp.domain.general.AbstractRepository;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@ManagedBean
@ApplicationScoped
public class AuthorRepository {

    @PersistenceContext(unitName = "books")
    private EntityManager entityManager;

    public Author edit(Author author){
        return entityManager.merge(author);
    }

    public long getCount(){
        return entityManager.createNamedQuery("author.getCount",Long.class).getSingleResult();
    }

    public Author findById(int id){
        return  entityManager.find(Author.class,id);
    }

    public List<Author> findByIds(List<Integer> authorsIds){
        return entityManager.createNamedQuery("author.findByIds",Author.class)
                .setParameter("authorsIds",authorsIds)
                .getResultList();
    }

    Author add(Author author){
        entityManager.persist(author);
        return author;
    }

    List<Author> getInRange(int from, int quantity){
        return buildFindAllQuery()
                .setFirstResult(from)
                .setMaxResults(quantity)
                .getResultList();
    }

    List<Author> findByNamePrefix(String namePrefix){
        return entityManager.createNamedQuery("author.findByNamePrefix",Author.class)
                .setParameter("namePrefix","%"+namePrefix+"%")
                .getResultList();
    }

    private TypedQuery<Author> buildFindAllQuery(){
       return entityManager.createNamedQuery("author.findAll",Author.class);
    }
}
