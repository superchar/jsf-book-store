package com.dataart.booksapp.presenters.book;

import com.dataart.booksapp.modules.author.AuthorService;
import com.dataart.booksapp.modules.author.AuthorViewModel;
import com.dataart.booksapp.modules.book.BookService;
import com.dataart.booksapp.modules.book.BookViewModel;
import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.genre.*;
import com.dataart.booksapp.presenters.general.AbstractPresenter;
import com.dataart.booksapp.presenters.user.UserData;
import com.dataart.booksapp.routing.Router;
import com.dataart.booksapp.routing.Routes;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Inject
    private UserData userData;

    @Inject
    private Router router;

    public List<AuthorViewModel> completeAuthor(String namePrefix) {
        return authorService.findByNamePrefix(namePrefix);
    }

    public List<GenreViewModel> completeGenre(String namePrefix) {
        return genreService.findByNamePrefix(namePrefix)
                .stream()
                .filter(g -> !bookData.getCurrentSelectedBook().getGenres().contains(g))
                .collect(Collectors.toList());
    }

    public Routes processBookEditing(){
        try{
            bookService.edit(bookData.getCurrentSelectedBook());
        }
        catch (NotExistsException ex){
            createGlobalMessage(ex.getMessage());
            return null;
        }
        return router.moveToBooksList();
    }

    public Routes addNewBook() {
        try {
            bookService.addNew(bookData.getCurrentSelectedBook(),userData.getCurrentUser());
        } catch (NotExistsException ex) {
            createGlobalMessage(ex.getMessage());
            return null;
        }
        catch (IOException ex){
            createGlobalMessage("Error occurred during reading book content");
            return null;
        }
        return Routes.booksList;
    }

}
