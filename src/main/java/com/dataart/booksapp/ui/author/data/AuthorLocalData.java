package com.dataart.booksapp.ui.author.data;

import com.dataart.booksapp.domain.author.AuthorViewModel;
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
public class AuthorLocalData extends AbstractDataBean<AuthorViewModel> implements Serializable {

    public AuthorLocalData(){
        super(new AuthorViewModel());
    }
}
