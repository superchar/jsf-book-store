package com.dataart.booksapp.ui.book;

import com.dataart.booksapp.ui.book.data.UserCreatedBookLocalData;
import com.dataart.booksapp.ui.book.helpers.FileDownloadHelper;
import com.dataart.booksapp.domain.book.BookService;
import com.dataart.booksapp.ui.book.data.BookLocalData;
import com.dataart.booksapp.ui.book.data.BookSessionData;
import com.dataart.booksapp.ui.general.ControlsLocalState;
import com.dataart.booksapp.ui.general.helpers.MessageHelper;
import com.dataart.booksapp.ui.user.UserSessionData;
import com.dataart.booksapp.ui.general.routing.Router;
import com.dataart.booksapp.ui.general.routing.Routes;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class BookPresenter implements Serializable {

    @EJB
    private BookService bookService;

    @Inject
    private Router router;

    @Inject
    private BookLocalData bookLocalData;

    @Inject
    private BookSessionData bookSessionData;

    @Inject
    private UserSessionData userSessionData;

    @Inject
    private ControlsLocalState controlsLocalState;

    @Inject
    private UserCreatedBookLocalData userCreatedBookLocalData;

    public Routes requestBookEditing(){
        bookSessionData.setCurrentEntity(bookLocalData.getCurrentEntity());
        return router.moveToBookEditing();
    }

    public Routes processBookEditing(){
        try{
            bookService.edit(bookSessionData.getCurrentEntity());
        }
        catch (IOException ex){
            MessageHelper.createGlobalMessage("Error during loading book data");
            return null;
        }
        return router.moveToBooksList();
    }

    public Routes processBookAdding() {
        try {
            bookService.add(bookLocalData.getCurrentEntity(), userSessionData.getCurrentUser());
        }
        catch (IOException ex){
            MessageHelper.createGlobalMessage("Error occurred during reading book content");
            return null;
        }
        return Routes.booksList;
    }


    public void removeBook() {
        bookService.remove(userCreatedBookLocalData.getCurrentEntity(), userSessionData.getCurrentUser());
        controlsLocalState.disableDeleting();
    }

    public Routes downloadBook() {
        try {
            FileDownloadHelper.downloadContentAsTextFile(bookLocalData.getCurrentEntity().getTitle(), bookLocalData.getCurrentEntity().getBookDataText().getBytes());
        } catch (IOException ex) {
            MessageHelper.createGlobalMessage("Error occurred during book downloading");
        }
        return null;
    }

    public Routes readBook(){
        bookSessionData.setCurrentEntity(bookLocalData.getCurrentEntity());
        return router.moveToBookReading();
    }

}
