package com.dataart.booksapp.presenters.user.favorite_books;

import com.dataart.booksapp.modules.book.BookViewModel;
import com.dataart.booksapp.modules.general.NotExistsException;
import com.dataart.booksapp.modules.user.UserService;
import com.dataart.booksapp.presenters.general.LazyDataModelBase;
import com.dataart.booksapp.presenters.user.UserData;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vlobyntsev on 15.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class FavoriteBooksLazyDataModel extends LazyDataModelBase<BookViewModel> implements Serializable {

    @EJB
    private UserService userService;

    @Inject
    private UserData userData;

    @Override
    protected int getRowsQuantity()  {
        try{
            return (int)userService.getFavoriteBooksCount(userData.getCurrentUser());
        }
        catch (NotExistsException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<BookViewModel> getEntitiesFor(int first, int quantity) {
        try{
            return userService.getFavoriteBooks(first,quantity,userData.getCurrentUser());
        }
        catch (NotExistsException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected int getIdFromEntity(BookViewModel entity) {
        return entity.getIdBook();
    }
}
