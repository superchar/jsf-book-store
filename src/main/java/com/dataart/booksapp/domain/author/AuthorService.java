package com.dataart.booksapp.domain.author;

import com.dataart.booksapp.domain.general.exceptions.NotExistsException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@Local
public interface AuthorService {

    List<AuthorViewModel> findByNamePrefix(String namePrefix);

    AuthorViewModel findById(int id);

    List<AuthorViewModel> getInRange(int from, int quantity);

    long getCount();

    void add(AuthorViewModel addedAuthorModel);

    void edit(AuthorViewModel authorViewModel);

}
