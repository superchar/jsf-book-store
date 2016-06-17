package com.dataart.booksapp.presenters.author.list;

import com.dataart.booksapp.modules.author.AuthorViewModel;
import com.dataart.booksapp.presenters.author.AuthorData;
import com.dataart.booksapp.presenters.general.CheckingUtil;
import com.dataart.booksapp.routing.Routes;

import javax.annotation.ManagedBean;
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
public class AuthorListPresenter implements Serializable {

    @Inject
    private AuthorData authorData;

    public Routes requestAuthorAdding(){
        authorData.setCurrentSelectedAuthor(new AuthorViewModel());
        return Routes.addingAuthor;
    }

    public Routes requestAuthorEditing(){
        if(CheckingUtil.wasEntitySelectedForEditing(authorData.getCurrentSelectedAuthor(), AuthorViewModel::getAuthorId)){
            return Routes.editingAuthor;
        }
        return null;
    }

}
