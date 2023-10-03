package ru.javamentor.springmvc.service;

import ru.javamentor.springmvc.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllUsers();

    void save(Role role);

    void deleteById(Long id);

    Role showUserById(Long id);
}
