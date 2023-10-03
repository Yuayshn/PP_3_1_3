package ru.javamentor.springmvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javamentor.springmvc.model.Role;
import ru.javamentor.springmvc.model.User;
import ru.javamentor.springmvc.service.RoleService;
import ru.javamentor.springmvc.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    public void initializedDataBase() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));
        Set<Role> adminRole = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        Set<Role> allRoles = new HashSet<>();
        adminRole.add(roleService.showUserById(1L));
        userRole.add(roleService.showUserById(2L));
        allRoles.add(roleService.showUserById(1L));
        allRoles.add(roleService.showUserById(2L));
        userService.addUser(new User((short) 10, "Admin", "admin" , adminRole));
        userService.addUser(new User((short) 15, "User", "user", userRole));
        userService.addUser(new User((short) 20,"Java", "hotjava", allRoles));
    }
}
