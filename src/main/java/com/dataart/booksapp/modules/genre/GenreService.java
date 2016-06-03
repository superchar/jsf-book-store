package com.dataart.booksapp.modules.genre;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
public interface GenreService {

    List<GenreViewModel> findByNamePrefix(String namePrefix);
    GenreViewModel findById(int id);
}
