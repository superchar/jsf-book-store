package com.dataart.booksapp.modules.genre;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Genre> findByIds(List<Integer> genresIds){
        return entityManager.createNamedQuery("genre.findByIds",Genre.class)
                .setParameter("genresIds",genresIds)
                .getResultList();
    }

    public Genre findById(int id){
        return entityManager.find(Genre.class,id);
    }
}
