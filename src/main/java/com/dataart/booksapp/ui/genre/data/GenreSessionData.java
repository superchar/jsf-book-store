package com.dataart.booksapp.ui.genre.data;

import com.dataart.booksapp.domain.genre.GenreViewModel;
import com.dataart.booksapp.ui.general.data.AbstractDataBean;

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
public class GenreSessionData extends AbstractDataBean<GenreViewModel> implements Serializable {

    public GenreSessionData(){
        super(new GenreViewModel());
    }
}
