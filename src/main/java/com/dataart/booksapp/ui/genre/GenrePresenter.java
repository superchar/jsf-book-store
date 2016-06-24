package com.dataart.booksapp.ui.genre;

import com.dataart.booksapp.domain.general.exceptions.ExistsWithException;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
import com.dataart.booksapp.domain.genre.GenreService;
import com.dataart.booksapp.ui.general.helpers.MessageHelper;
import com.dataart.booksapp.ui.genre.data.GenreLocalData;
import com.dataart.booksapp.ui.genre.data.GenreSessionData;
import com.dataart.booksapp.ui.general.routing.Router;
import com.dataart.booksapp.ui.general.routing.Routes;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 14.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class GenrePresenter implements Serializable {

    @Inject
    private Router router;

    @EJB
    private GenreService genreService;

    @Inject
    private GenreLocalData genreLocalData;

    @Inject
    private GenreSessionData genreSessionData;

    public Routes addGenre(){
        genreService.add(genreLocalData.getCurrentEntity());
        return router.moveToGenresList();
    }

    public Routes editGenre() {
        genreService.edit(genreSessionData.getCurrentEntity());
        return router.moveToGenresList();
    }

    public Routes requestGenreEditing(){
        genreSessionData.setCurrentEntity(genreLocalData.getCurrentEntity());
        return router.moveToGenreEditing();
    }
}
