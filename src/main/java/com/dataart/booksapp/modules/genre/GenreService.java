package com.dataart.booksapp.modules.genre;

import com.dataart.booksapp.modules.general.NotExistsException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@Local
public interface GenreService {

    List<GenreViewModel> findByNamePrefix(String namePrefix);

    long getCount();

    GenreViewModel findById(int id);

    List<GenreViewModel> getInRange(int first,int quantity);

    void editGenre(GenreViewModel editingGenreViewModel) throws NotExistsException;

    void addGenre(GenreViewModel addingGenreModel);

    void remove(GenreViewModel removingGenreModel) throws NotExistsException;

    boolean canEditGenre(GenreViewModel genreViewModel) throws NotExistsException;
}
