package com.dataart.booksapp.presenters.author.list;

import com.dataart.booksapp.modules.author.AuthorService;
import com.dataart.booksapp.modules.author.AuthorViewModel;
import com.dataart.booksapp.presenters.general.LazyDataModelBase;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
