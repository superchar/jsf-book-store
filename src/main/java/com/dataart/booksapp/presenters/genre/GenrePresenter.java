package com.dataart.booksapp.presenters.genre;

import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.genre.GenreService;
import com.dataart.booksapp.modules.genre.GenreViewModel;
import com.dataart.booksapp.presenters.general.AbstractPresenter;
import com.dataart.booksapp.presenters.genre.list.GenreData;
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
public class GenrePresenter extends AbstractPresenter implements Serializable {

    @Inject
    private GenreData genreData;

    @Inject
    private Router router;

    @EJB
    private GenreService genreService;

    public Routes addGenre(){
        genreService.addGenre(genreData.getCurrentSelectedGenre());
        return router.moveToGenresList();
    }

    public Routes editGenre() {
        try{
            genreService.editGenre(genreData.getCurrentSelectedGenre());
            return router.moveToGenresList();
        }
        catch (NotExistsException ex){
            createGlobalMessage("Edited genre does not exist");
        }
        return null;
    }
}
