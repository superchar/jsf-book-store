package com.dataart.booksapp.modules.genre;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@ManagedBean
@ApplicationScoped
public class GenreRepository {

    @PersistenceContext(unitName = "books")
    private EntityManager entityManager;

    public List<Genre> findByNamePrefix(String namePrefix) {
        return entityManager.createNamedQuery("genre.findByPrefix", Genre.class)
                .setParameter("namePrefix", "%" + namePrefix + "%")
                .getResultList();
    }

    public Genre findById(int id){
        return entityManager.find(Genre.class,id);
    }
}
