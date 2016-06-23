package com.dataart.booksapp.domain.genre;

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

    public List<Genre> findByIds(List<Integer> genresIds){
        return entityManager.createNamedQuery("genre.findByIds",Genre.class)
                .setParameter("genresIds",genresIds)
                .getResultList();
    }

    public long getCount(){
        return entityManager.createNamedQuery("genre.getCount",Long.class)
                .getSingleResult();
    }

    public boolean doesExistsWithName(String name){
        return entityManager.createNamedQuery("genre.getCountForName",Long.class)
                .setParameter("name",name)
                .getSingleResult() > 0;
    }

    public List<Genre> getInRange(int first,int quantity){
        return entityManager.createNamedQuery("genre.findAll",Genre.class)
                .setFirstResult(first)
                .setMaxResults(quantity)
                .getResultList();
    }

    public Genre findById(int id){
        return entityManager.find(Genre.class,id);
    }

    public Genre addGenre(Genre genre){
        entityManager.persist(genre);
        return genre;
    }

    public Genre editGenre(Genre genre){
        return entityManager.merge(genre);
    }

    public Genre removeGenre(Genre genre){
        entityManager.remove(genre);
        return genre;
    }

}
