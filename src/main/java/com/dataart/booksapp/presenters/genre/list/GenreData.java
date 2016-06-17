package com.dataart.booksapp.presenters.genre.list;

import com.dataart.booksapp.modules.genre.GenreViewModel;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 14.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class GenreData implements Serializable {

    private GenreViewModel currentSelectedGenre;

    public GenreViewModel getCurrentSelectedGenre() {
        return currentSelectedGenre;
    }

    public void setCurrentSelectedGenre(GenreViewModel currentSelectedGenre) {
        this.currentSelectedGenre = currentSelectedGenre;
    }
}
