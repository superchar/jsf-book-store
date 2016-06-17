package com.dataart.booksapp.presenters.book.list;

import com.dataart.booksapp.helpers.FileDownloadHelper;
import com.dataart.booksapp.modules.book.BookService;
import com.dataart.booksapp.modules.book.BookViewModel;
import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.user.UserService;
import com.dataart.booksapp.presenters.book.BookData;
import com.dataart.booksapp.presenters.general.AbstractPresenter;
import com.dataart.booksapp.presenters.general.ControlsState;
import com.dataart.booksapp.presenters.user.UserData;
import com.dataart.booksapp.routing.Router;
import com.dataart.booksapp.routing.Routes;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 06.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class BooksListPresenter extends AbstractPresenter implements Serializable {


    @Inject
    private BookData bookData;

    @Inject
    private UserData userData;

    @EJB
    private UserService userService;

    @EJB
    private BookService bookService;

    @Inject
    private Router router;

    @Inject
    private ControlsState controlsState;

    public Routes requestBookAdding() {
        bookData.setCurrentSelectedBook(new BookViewModel());
        return router.moveToBookAdding();
    }

    public Routes addToFavorites() {
        try {
            userService.addBookToUserFavorites(bookData.getCurrentSelectedBook(), userData.getCurrentUser());
        } catch (NotExistsException e) {
            createGlobalMessage("Adding book to favorite was failed because on not existing of the of the entities");
            return null;
        }
        return router.moveToMyProfile();
    }

    public void removeBook() {
        try {
            bookService.remove(bookData.getCurrentSelectedForDeletingBook(), userData.getCurrentUser());
            controlsState.disableDeleting();
        } catch (NotExistsException e) {
            createGlobalMessage("Book selected for removing does not exist");
        }
    }

    public Routes editBook() {
        return router.moveToBookEditing();
    }

    public Routes downloadBook() {
        try {
            FileDownloadHelper.downloadContentAsTextFile(bookData.getCurrentSelectedBook().getTitle(), bookData.getCurrentSelectedBook().getBookDataText().getBytes());
        } catch (IOException ex) {
            createGlobalMessage("Error occurred during book downloading");
        }
        return null;
    }
}
