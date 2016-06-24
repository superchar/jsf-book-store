package com.dataart.booksapp.ui.general.pagination;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by vlobyntsev on 14.06.2016.
 */
public abstract class AbstractLazyDataModelBase<T> extends LazyDataModel<T> {

    private List<T> fetchedEntities;

    protected abstract int getRowsQuantity();

    protected abstract List<T> getEntitiesFor(int first,int quantity);

    protected abstract int getIdFromEntity(T entity);

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.setRowCount(getRowsQuantity());
        fetchedEntities = getEntitiesFor(first,pageSize);
        return fetchedEntities;
    }

    @Override
    public Object getRowKey(T entity) {
        return getIdFromEntity(entity);
    }

    @Override
    public T getRowData(String rowKey) {
        int id = Integer.parseInt(rowKey);
        for(T fetchedEntity: fetchedEntities){
            if(id == getIdFromEntity(fetchedEntity)){
                return fetchedEntity;
            }
        }
        return null;
    }
}
