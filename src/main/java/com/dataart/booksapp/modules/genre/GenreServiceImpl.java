package com.dataart.booksapp.modules.genre;

import com.dataart.booksapp.modules.general.GeneralMapper;
import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.general.Preconditions;

import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
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


@Stateless
public class GenreServiceImpl implements GenreService {

    @Inject
    private GenreRepository genreRepository;

    @Override
    public List<GenreViewModel> findByNamePrefix(String namePrefix) {
        return GenreModelMapper.mapFromDomainList(genreRepository.findByNamePrefix(namePrefix));
    }

    @Override
    public GenreViewModel findById(int id) {
        return GenreModelMapper.mapFromDomain(genreRepository.findById(id));
    }

    @Override
    public long getCount() {
        return genreRepository.getCount();
    }

    @Override
    public List<GenreViewModel> getInRange(int first, int quantity) {
        return GenreModelMapper.mapFromDomainList(genreRepository.getInRange(first, quantity));
    }

    @Override
    public void editGenre(GenreViewModel editedGenreViewModel) throws NotExistsException {
        Genre oldGenre = loadGenreFromViewModel(editedGenreViewModel);
        if (canEditGenre(oldGenre, editedGenreViewModel)) {
            oldGenre.setName(editedGenreViewModel.getName());
            genreRepository.editGenre(oldGenre);
        }
    }

    @Override
    public void addGenre(GenreViewModel addingGenreModel) {
        if (!genreRepository.doesExistsWithName(addingGenreModel.getName())) {
            genreRepository.addGenre(GenreModelMapper.mapFromView(addingGenreModel));
        }
    }

    @Override
    public void remove(GenreViewModel removingGenreModel) throws NotExistsException {
        Genre removingGenre = loadGenreFromViewModel(removingGenreModel);
        genreRepository.removeGenre(removingGenre);
    }

    private boolean canEditGenre(Genre genre, GenreViewModel genreViewModel) {
        return !genre.getName().equals(genreViewModel.getName()) && !genreRepository.doesExistsWithName(genreViewModel.getName());
    }

    public boolean canEditGenre(GenreViewModel genreViewModel) throws NotExistsException{
        Genre genre = loadGenreFromViewModel(genreViewModel);
        return canEditGenre(genre,genreViewModel);
    }


    private Genre loadGenreFromViewModel(GenreViewModel genreViewModel) throws NotExistsException {
        return GeneralMapper.loadModelFromViewModel(genreViewModel, GenreViewModel::getIdGenre, genreRepository::findById);
    }
}
