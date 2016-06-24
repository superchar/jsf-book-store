package com.dataart.booksapp.domain.genre;

import com.dataart.booksapp.domain.general.exceptions.ExistsWithException;

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

    void edit(GenreViewModel editingGenreViewModel);

    void add(GenreViewModel addingGenreModel) ;

    void remove(GenreViewModel removingGenreModel);
}
