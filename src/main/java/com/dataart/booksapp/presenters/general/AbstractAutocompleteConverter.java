package com.dataart.booksapp.presenters.general;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
public abstract class AbstractAutocompleteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        int id = Integer.parseInt(value);
        return getObjectFromId(id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if(value!=null){
            return getStringFromObject(value);
        }
        return null;
    }

    protected abstract Object getObjectFromId(int id);
    protected abstract String getStringFromObject(Object object);
}
