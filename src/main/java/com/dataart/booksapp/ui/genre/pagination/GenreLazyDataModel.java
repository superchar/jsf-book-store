package com.dataart.booksapp.ui.genre.pagination;

import com.dataart.booksapp.domain.genre.GenreService;
import com.dataart.booksapp.domain.genre.GenreViewModel;
import com.dataart.booksapp.ui.general.pagination.AbstractLazyDataModelBase;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vlobyntsev on 14.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class GenreLazyDataModel extends AbstractLazyDataModelBase<GenreViewModel> {

    @EJB
    private GenreService genreService;

    @Override
    protected int getRowsQuantity() {
        return (int)genreService.getCount();
    }

    @Override
    protected List<GenreViewModel> getEntitiesFor(int first, int quantity) {
        return genreService.getInRange(first,quantity);
    }

    @Override
    protected int getIdFromEntity(GenreViewModel entity) {
        return entity.getIdGenre();
    }
}
