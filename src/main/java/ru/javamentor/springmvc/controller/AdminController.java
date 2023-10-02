package ru.javamentor.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springmvc.model.Role;
import ru.javamentor.springmvc.model.User;
import ru.javamentor.springmvc.repositories.RoleRepository;
import ru.javamentor.springmvc.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/hello")
    public String adminPrintAllUsersList(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAllUsers());
        return "admin/helloList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, ModelMap model) {
        // получим 1 чела по айди
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("user") User user, ModelMap modelMap) {
        List<Role> roles = roleRepository.findAll();
        modelMap.addAttribute("listRoles", roles);
        return "create";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                        ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "create";
        } else {
            List<Role> roles = roleRepository.findAll();
            modelMap.addAttribute("listRoles", roles);
            userService.addUser(user);
            return "redirect:/hello";
        }
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap model) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("listRoles", roles);
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }
    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id, @RequestParam("listRoles") List<Long> rolesId) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            List<Role> roles = roleRepository.findAll(rolesId);
            user.setRole(roles);
            userService.updateUser(user);
            return "redirect:/hello";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/hello";
    }
}
