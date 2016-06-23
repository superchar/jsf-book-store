package com.dataart.booksapp.ui.genre;

import com.dataart.booksapp.domain.general.exceptions.ExistsException;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
import com.dataart.booksapp.domain.genre.GenreService;
import com.dataart.booksapp.ui.general.AbstractPresenter;
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
public class GenrePresenter extends AbstractPresenter implements Serializable {

    @Inject
    private Router router;

    @EJB
    private GenreService genreService;

    @Inject
    private GenreLocalData genreLocalData;

    @Inject
    private GenreSessionData genreSessionData;

    public Routes addGenre(){
        genreService.addGenre(genreLocalData.getCurrentEntity());
        return router.moveToGenresList();
    }

    public Routes editGenre() {
        try{
            genreService.editGenre(genreSessionData.getCurrentEntity()); //It it pretty strange, that methods may throw both existing and not existing exceptions.
            return router.moveToGenresList();//NotExistException may be thrown in case when genre which does not exist in db is tried to edit.
        }                                    //ExistException may be thrown when new genre name already belongs to some other genre, for preventing duplications.
        catch (NotExistsException ex){
            createGlobalMessage("Edited genre does not exist");
        }
        catch (ExistsException ex){
            createGlobalMessage(ex.getMessage());
        }
        return null;
    }

    public Routes requestGenreEditing(){
        genreSessionData.setCurrentEntity(genreLocalData.getCurrentEntity());
        return router.moveToGenreEditing();
    }
}
