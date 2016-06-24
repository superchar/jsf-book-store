package com.dataart.booksapp.ui.book.data;

import com.dataart.booksapp.domain.book.BookViewModel;
import com.dataart.booksapp.ui.general.data.AbstractDataBean;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vlobyntsev on 23.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class BookSessionData extends AbstractDataBean<BookViewModel> implements Serializable {

    private int readingFontSize = 15;

    public BookSessionData() {
        super(new BookViewModel());
    }

    public int getReadingFontSize() {
        return readingFontSize;
    }

    public void setReadingFontSize(int readingFontSize) {
        this.readingFontSize = readingFontSize;
    }

}
