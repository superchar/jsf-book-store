package com.dataart.booksapp.ui.book.data;

import com.dataart.booksapp.domain.book.BookViewModel;
import com.dataart.booksapp.ui.general.data.AbstractDataBean;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 23.06.2016.
 */
@ManagedBean
@ViewScoped
@Named
public class UserCreatedBookLocalData extends AbstractDataBean<BookViewModel> implements Serializable {

    public UserCreatedBookLocalData(){
        super(new BookViewModel());
    }
}
