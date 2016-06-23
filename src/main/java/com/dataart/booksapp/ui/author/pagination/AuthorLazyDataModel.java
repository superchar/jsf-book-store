package com.dataart.booksapp.ui.author.pagination;

import com.dataart.booksapp.domain.author.AuthorService;
import com.dataart.booksapp.domain.author.AuthorViewModel;
import com.dataart.booksapp.ui.general.LazyDataModelBase;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vlobyntsev on 14.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class AuthorLazyDataModel extends LazyDataModelBase<AuthorViewModel> implements Serializable {

    @EJB
    private AuthorService authorService;

    @Override
    protected int getRowsQuantity() {
        return (int)authorService.getCount();
    }

    @Override
    protected List<AuthorViewModel> getEntitiesFor(int first, int quantity) {
        return authorService.getAuthorsInRange(first,quantity);
    }

    @Override
    protected int getIdFromEntity(AuthorViewModel entity) {
        return entity.getAuthorId();
    }
}
