package com.dataart.booksapp.presenters.book;

import com.dataart.booksapp.modules.author.AuthorService;
import com.dataart.booksapp.modules.author.AuthorViewModel;
import com.dataart.booksapp.modules.book.BookService;
import com.dataart.booksapp.modules.book.BookViewModel;
import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.genre.*;
import com.dataart.booksapp.presenters.general.AbstractPresenter;
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
public class BookPresenter extends AbstractPresenter implements Serializable {

    @Inject
    private AuthorService authorService;

    @Inject
    private GenreService genreService;

    @Inject
    private BookService bookService;

    @Inject
    private BookData bookData;

    public List<AuthorViewModel> completeAuthor(String namePrefix){
        return authorService.findByNamePrefix(namePrefix);
    }

    public List<GenreViewModel> completeGenre(String namePrefix){
        return genreService.findByNamePrefix(namePrefix);
    }

    public Routes addNewBook(){
        BookViewModel bookViewModel = buildViewModelFromBookData();
        try{
            bookService.addNew(bookViewModel);
        }
        catch (NotExistsException ex){
            createGlobalMessage(ex.getMessage());
            return null;
        }
        return Routes.successfulAuthorization;
    }

    private BookViewModel buildViewModelFromBookData(){
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setTitle(bookData.getTitle());
        bookViewModel.setIsbn(bookData.getIsbn());
        bookViewModel.setDescription(bookData.getDescription());
        bookViewModel.setAuthor(bookData.getAuthor());
        bookViewModel.setGenre(bookData.getGenre());
        return bookViewModel;
    }
}
