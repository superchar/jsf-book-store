package com.dataart.booksapp.ui.user.profile;

import com.dataart.booksapp.domain.book.BookViewModel;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;
import com.dataart.booksapp.domain.user.UserService;
import com.dataart.booksapp.ui.general.pagination.AbstractLazyDataModelBase;
import com.dataart.booksapp.ui.user.UserSessionData;

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
public class FavoriteBooksAbstractLazyDataModelBase extends AbstractLazyDataModelBase<BookViewModel> implements Serializable {

    @EJB
    private UserService userService;

    @Inject
    private UserSessionData userSessionData;

    @Override
    protected int getRowsQuantity()  {
        try{
            return (int)userService.getFavoriteBooksCount(userSessionData.getCurrentUser());
        }
        catch (NotExistsException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<BookViewModel> getEntitiesFor(int first, int quantity) {
        try{
            return userService.getFavoriteBooks(first,quantity, userSessionData.getCurrentUser());
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
