package com.dataart.booksapp.modules.user;

import javax.ejb.Local;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@Local
public interface UserService {
    boolean areCredentialsValid(UserCredentials userCredentials);
    User createNew(User user) throws UserExistsException;
}
