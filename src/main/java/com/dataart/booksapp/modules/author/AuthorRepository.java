package com.dataart.booksapp.modules.author;


import com.dataart.booksapp.modules.general.AbstractRepository;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@ManagedBean
@ApplicationScoped
public class AuthorRepository extends AbstractRepository {

    @PersistenceContext(unitName = "books")
    private EntityManager entityManager;

    public List<Author> findAll(){
        return entityManager
                .createNamedQuery("author.findAll",Author.class)
                .getResultList();
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
}
