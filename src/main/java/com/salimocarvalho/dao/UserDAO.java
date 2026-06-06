package com.salimocarvalho.dao;

import com.salimocarvalho.model.User;

import java.util.List;

public interface UserDAO {
    void createUser(User user);
    User getUser(int id);
    List<User> showAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}
