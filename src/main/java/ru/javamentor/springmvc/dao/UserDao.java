package ru.javamentor.springmvc.dao;


import ru.javamentor.springmvc.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    User getUserById(Long id);
    void addUser(User user);
    void removeUser(Long id);
    void updateUser(User user);
    User getUserByLogin(String username);
}
