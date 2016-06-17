package com.dataart.booksapp.presenters.genre.list;

import com.dataart.booksapp.modules.genre.Genre;
import com.dataart.booksapp.modules.genre.GenreService;
import com.dataart.booksapp.modules.genre.GenreViewModel;
import com.dataart.booksapp.routing.Router;
import com.dataart.booksapp.routing.Routes;

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
public class GenreListPresenter implements Serializable {

    @Inject
    private GenreData genreData;

    @Inject
    private Router router;

    public Routes requestGenreEditing() {
        if(wasGenreSelectedForEditing()){
            return router.moveToGenreEditing();
        }
        return null;
    }

    public Routes requestGenreAdding() {
        genreData.setCurrentSelectedGenre(new GenreViewModel());
        return Routes.addingGenre;
    }

    private boolean wasGenreSelectedForEditing() {
        return genreData.getCurrentSelectedGenre() != null && genreData.getCurrentSelectedGenre().getIdGenre() > 0;
    }
}
