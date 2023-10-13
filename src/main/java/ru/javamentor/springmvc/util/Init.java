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
        roleService.addRole(new Role("ADMIN"));
        roleService.addRole(new Role("USER"));

        Set<Role> adminRole = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        Set<Role> allRoles = new HashSet<>();

        adminRole.add(roleService.getRoleById(1L));
        userRole.add(roleService.getRoleById(2L));
        allRoles.add(roleService.getRoleById(1L));
        allRoles.add(roleService.getRoleById(2L));

        userService.saveUser(new User("Alesha", "Popovich", 23, "alesha@mail.com",
                "User", "user", userRole));
        userService.saveUser(new User("Elon", "Mask", 30, "elonFacebook@facebook.com",
                "Admin", "admin", adminRole));
        userService.saveUser(new User("Java", "Hot", 99, "helloWorld@yandex.ru",
                "Java", "java", allRoles));
    }
}
