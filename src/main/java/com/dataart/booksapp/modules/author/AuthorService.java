package com.dataart.booksapp.modules.author;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
public interface AuthorService {

    List<AuthorViewModel> findByNamePrefix(String namePrefix);

    AuthorViewModel findById(int id);
}
