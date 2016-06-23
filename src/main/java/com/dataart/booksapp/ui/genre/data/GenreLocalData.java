package com.dataart.booksapp.ui.genre.data;

import com.dataart.booksapp.domain.genre.GenreViewModel;
import com.dataart.booksapp.ui.general.data.AbstractDataBean;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 23.06.2016.
 */
@ManagedBean
@ViewScoped
@Named
public class GenreLocalData extends AbstractDataBean<GenreViewModel> implements Serializable {

    public GenreLocalData(){
        super(new GenreViewModel());
    }
}
