package ru.javamentor.springmvc.service;


import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javamentor.springmvc.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();
    User getUserById(Long id);
    void addUser(User user);
    void removeUser(Long id);
    void updateUser(@Valid User user);
    User getUserByLogin(String username);
    UserDetails loadUserByUsername(String username);


}
