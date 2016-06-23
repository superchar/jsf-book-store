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
public class AuthorRepository extends AbstractRepository {

    @PersistenceContext(unitName = "books")
    private EntityManager entityManager;

    public Author addAuthor(Author author){
        entityManager.persist(author);
        return author;
    }

    public Author edit(Author author){
        return entityManager.merge(author);
    }

    public List<Author> getAuthorsInRange(int from,int quantity){
        return buildFindAllQuery()
                .setFirstResult(from)
                .setMaxResults(quantity)
                .getResultList();
    }

    public List<Author> findAll(){
        return buildFindAllQuery().getResultList();
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

    public List<Author> findByNamePrefix(String namePrefix){
        return entityManager.createNamedQuery("author.findByNamePrefix",Author.class)
                .setParameter("namePrefix","%"+namePrefix+"%")
                .getResultList();
    }

    public Author delete(Author author){
        entityManager.remove(author);
        return author;
    }

    private TypedQuery<Author> buildFindAllQuery(){
       return entityManager.createNamedQuery("author.findAll",Author.class);
    }
}
