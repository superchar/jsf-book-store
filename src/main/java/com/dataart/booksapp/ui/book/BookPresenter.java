package com.dataart.booksapp.ui.book;

import com.dataart.booksapp.domain.book.BookViewModel;
import com.dataart.booksapp.ui.book.data.UserCreatedBookLocalData;
import com.dataart.booksapp.ui.book.helpers.FileDownloadHelper;
import com.dataart.booksapp.domain.author.AuthorService;
import com.dataart.booksapp.domain.author.AuthorViewModel;
import com.dataart.booksapp.domain.book.BookService;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
import com.dataart.booksapp.domain.genre.*;
import com.dataart.booksapp.domain.user.UserService;
import com.dataart.booksapp.ui.book.data.BookLocalData;
import com.dataart.booksapp.ui.book.data.BookSessionData;
import com.dataart.booksapp.ui.general.AbstractPresenter;
import com.dataart.booksapp.ui.general.ControlsLocalState;
import com.dataart.booksapp.ui.user.UserSessionData;
import com.dataart.booksapp.ui.general.routing.Router;
import com.dataart.booksapp.ui.general.routing.Routes;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class BookPresenter extends AbstractPresenter implements Serializable {

    @Inject
    private AuthorService authorService;

    @Inject
    private GenreService genreService;

    @Inject
    private BookService bookService;

    @Inject
    private UserService userService;

    @Inject
    private BookLocalData bookLocalData;

    @Inject
    private UserCreatedBookLocalData userCreatedBookLocalData;

    @Inject
    private BookSessionData bookSessionData;

    @Inject
    private UserSessionData userSessionData;

    @Inject
    private Router router;

    @Inject
    private ControlsLocalState controlsLocalState;

    public List<AuthorViewModel> completeAuthor(String namePrefix) {
        return authorService.findByNamePrefix(namePrefix);
    }

    public List<GenreViewModel> completeGenre(String namePrefix) {
        return genreService.findByNamePrefix(namePrefix)
                .stream()
                .filter(g -> !bookLocalData.getCurrentEntity().getGenres().contains(g))
                .collect(Collectors.toList());
    }

    public Routes requestBookEditing(){
        bookSessionData.setCurrentEntity(bookLocalData.getCurrentEntity());
        return router.moveToBookEditing();
    }

    public Routes processBookEditing(){
        try{
            bookService.edit(bookSessionData.getCurrentEntity());
        }
        catch (NotExistsException ex){
            createGlobalMessage("Editing book does not exist");
            return null;
        }
        catch (IOException ex){
            createGlobalMessage("Error during loading book data");
            return null;
        }
        return router.moveToBooksList();
    }

    public Routes processBookAdding() {
        try {
            bookService.addNew(bookLocalData.getCurrentEntity(), userSessionData.getCurrentUser());
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

    public Routes addToFavorites() {
        try {
            userService.addBookToUserFavorites(bookLocalData.getCurrentEntity(), userSessionData.getCurrentUser());
        } catch (NotExistsException e) {
            createGlobalMessage("Adding book to favorite was failed because on not existing of the of the entities");
            return null;
        }
        return router.moveToMyProfile();
    }

    public void removeBook() {
        try {
            bookService.remove(userCreatedBookLocalData.getCurrentEntity(), userSessionData.getCurrentUser());
            controlsLocalState.disableDeleting();
        } catch (NotExistsException e) {
            createGlobalMessage("Book selected for removing does not exist");
        }
    }

    public Routes downloadBook() {
        try {
            FileDownloadHelper.downloadContentAsTextFile(bookLocalData.getCurrentEntity().getTitle(), bookLocalData.getCurrentEntity().getBookDataText().getBytes());
        } catch (IOException ex) {
            createGlobalMessage("Error occurred during book downloading");
        }
        return null;
    }

    public Routes readBook(){
        BookViewModel current = bookLocalData.getCurrentEntity();
        bookSessionData.setCurrentEntity(bookLocalData.getCurrentEntity());
        return router.moveToBookReading();
    }

}
