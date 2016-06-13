package com.dataart.booksapp.modules.genre;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */

@ManagedBean
@ApplicationScoped
public class GenreServiceImpl implements GenreService {

    @Inject
    private GenreRepository genreRepository;

    public List<GenreViewModel> findByNamePrefix(String namePrefix) {
        return GenreModelMapper.mapFromDomainList(genreRepository.findByNamePrefix(namePrefix));
    }

    public GenreViewModel findById(int id) {
        return GenreModelMapper.mapFromDomain(genreRepository.findById(id));
    }
}
