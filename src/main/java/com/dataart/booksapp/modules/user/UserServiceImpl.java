package com.dataart.booksapp.modules.user;

import com.dataart.booksapp.modules.general.NotExistsException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by vlobyntsev on 31.05.2016.
 */
@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    public boolean areCredentialsValid(UserCredentials userCredentials) {
        User targetUser = userRepository.findByEmail(userCredentials.getEmail());
        return targetUser != null && targetUser.getPassword().equals(userCredentials.getPassword());
    }

    public User createNew(User user) throws NotExistsException {
        if (doesUserExist(user)) {
            throw new NotExistsException("User", "email", user.getEmail());
        }
        return userRepository.createNew(user);
    }

    private boolean doesUserExist(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        return existingUser != null;
    }
}
