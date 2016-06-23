package com.dataart.booksapp.domain.genre;

import com.dataart.booksapp.domain.general.exceptions.ExistsException;
import com.dataart.booksapp.domain.general.GeneralMapper;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
    public void editGenre(GenreViewModel editedGenreViewModel) throws NotExistsException,ExistsException {
        Genre oldGenre = loadGenreFromViewModel(editedGenreViewModel);
        if (!oldGenre.getName().equals(editedGenreViewModel.getName())) {
            if (genreRepository.doesExistsWithName(editedGenreViewModel.getName())) {
                throw new ExistsException("Genre", "name", editedGenreViewModel.getName());
            }
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

    private Genre loadGenreFromViewModel(GenreViewModel genreViewModel) throws NotExistsException {
        return GeneralMapper.loadModelFromViewModel(genreViewModel, GenreViewModel::getIdGenre, genreRepository::findById);
    }
}
