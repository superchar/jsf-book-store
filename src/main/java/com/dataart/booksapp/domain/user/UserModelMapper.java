package com.dataart.booksapp.domain.user;

/**
 * Created by vlobyntsev on 15.06.2016.
 */
public class UserModelMapper {

    public static User mapFromView(UserViewModel userViewModel){
        User user = new User();
        user.setEmail(userViewModel.getEmail());
        user.setFirstName(userViewModel.getFirstName());
        user.setLastName(userViewModel.getLastName());
        user.setIdUser(userViewModel.getIdUser());
        user.setPassword(userViewModel.getPassword());
        return user;
    }

    static UserViewModel mapFromDomain(User user){
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setEmail(user.getEmail());
        userViewModel.setFirstName(user.getFirstName());
        userViewModel.setLastName(user.getLastName());
        userViewModel.setIdUser(user.getIdUser());
        userViewModel.setPassword(user.getPassword());
        return userViewModel;
    }
}
