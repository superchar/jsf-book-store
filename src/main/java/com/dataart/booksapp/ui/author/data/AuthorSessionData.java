package com.dataart.booksapp.ui.author.data;

import com.dataart.booksapp.domain.author.AuthorViewModel;
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
public class AuthorSessionData extends AbstractDataBean<AuthorViewModel> implements Serializable {

    public AuthorSessionData(){
        super(new AuthorViewModel());
    }
}
