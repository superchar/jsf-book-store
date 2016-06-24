package com.dataart.booksapp.ui.user.profile;

import com.dataart.booksapp.domain.book.BookViewModel;
import com.dataart.booksapp.domain.user.UserViewModel;
import com.dataart.booksapp.ui.book.BookPresenter;
import com.dataart.booksapp.ui.book.data.BookLocalData;
import com.dataart.booksapp.ui.book.data.BookSessionData;
import com.dataart.booksapp.ui.book.data.UserCreatedBookLocalData;
import com.dataart.booksapp.ui.general.routing.Routes;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 23.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class UserProfilePresenter implements Serializable {

    @Inject
    private BookPresenter bookPresenter;

    @Inject
    private BookLocalData bookLocalData;

    @Inject
    private UserCreatedBookLocalData userCreatedBookLocalData;

    public Routes downloadCreatedBook(){
        replaceBookLocalDataWithUserCreated();
        return bookPresenter.downloadBook();
    }

    public Routes readCreatedBook(){
        replaceBookLocalDataWithUserCreated();
        return bookPresenter.readBook();
    }

    private void replaceBookLocalDataWithUserCreated(){
        bookLocalData.setCurrentEntity(userCreatedBookLocalData.getCurrentEntity());
    }
}
