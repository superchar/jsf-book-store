package com.dataart.booksapp.presenters.book;

import com.dataart.booksapp.modules.author.AuthorService;
import com.dataart.booksapp.modules.author.AuthorViewModel;
import com.dataart.booksapp.modules.genre.*;
import com.dataart.booksapp.routing.Routes;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@ManagedBean
@ApplicationScoped
@Named
public class BookPresenter implements Serializable {


    @Inject
    private AuthorService authorService;

    @Inject
    private GenreService genreService;


    @Inject
    private BookData bookData;

    public List<AuthorViewModel> completeAuthor(String namePrefix){
        return authorService.findByNamePrefix(namePrefix);
    }

    public List<GenreViewModel> completeGenre(String namePrefix){
        return genreService.findByNamePrefix(namePrefix);
    }

    public Routes addNewBook(){
        return Routes.successfulAuthorization;
    }

}
