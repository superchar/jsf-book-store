package com.dataart.booksapp.modules.author;

import com.dataart.booksapp.modules.general.NotExistsException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@Local
public interface AuthorService {

    List<AuthorViewModel> findByNamePrefix(String namePrefix);

    AuthorViewModel findById(int id);

    List<AuthorViewModel> getAuthorsInRange(int from,int quantity);

    long getCount();

    void addAuthor(AuthorViewModel addedAuthorModel);

    void editAuthor(AuthorViewModel authorViewModel) throws NotExistsException;

    void delete(AuthorViewModel authorViewModel) throws NotExistsException;
}
