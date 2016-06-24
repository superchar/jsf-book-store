package com.dataart.booksapp.domain.genre;

import com.dataart.booksapp.domain.general.Preconditions;
import com.dataart.booksapp.domain.general.exceptions.ExistsWithException;
import com.dataart.booksapp.domain.general.GeneralMapper;

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
        Preconditions.throwIllegalArgumentIfNegativeValue(first,quantity);
        return GenreModelMapper.mapFromDomainList(genreRepository.getInRange(first, quantity));
    }

    @Override
    public void edit(GenreViewModel editedGenreViewModel)  {
        throwIllegalArgumentIfGenreIsNull(editedGenreViewModel);
        Genre oldGenre = loadGenreFromViewModel(editedGenreViewModel);
        if (!oldGenre.getName().equals(editedGenreViewModel.getName())) {
            if (genreRepository.doesExistWithName(editedGenreViewModel.getName())) {
                throwExistWithName(editedGenreViewModel);
            }
            oldGenre.setName(editedGenreViewModel.getName());
            genreRepository.edit(oldGenre);
        }
    }

    @Override
    public void add(GenreViewModel addingGenreModel) {
        throwIllegalArgumentIfGenreIsNull(addingGenreModel);
        if (genreRepository.doesExistWithName(addingGenreModel.getName())) {
            throwExistWithName(addingGenreModel);
        }
        genreRepository.add(GenreModelMapper.mapFromView(addingGenreModel));
    }

    @Override
    public void remove(GenreViewModel removingGenreModel)  {
        throwIllegalArgumentIfGenreIsNull(removingGenreModel);
        genreRepository.remove(loadGenreFromViewModel(removingGenreModel));
    }

    private Genre loadGenreFromViewModel(GenreViewModel genreViewModel)  {
        return GeneralMapper.loadModelFromViewModel(genreViewModel, GenreViewModel::getIdGenre, genreRepository::findById);
    }

    private void throwIllegalArgumentIfGenreIsNull(GenreViewModel genreViewModel){
        Preconditions.throwIllegalArgumentIfParamIsNull(genreViewModel,"genreViewModel");
    }

    private void throwExistWithName(GenreViewModel genreViewModel) throws ExistsWithException{
        throw new ExistsWithException("Genre", "name", genreViewModel.getName());
    }
}
