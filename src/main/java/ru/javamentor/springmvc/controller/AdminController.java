package ru.javamentor.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springmvc.model.User;
import ru.javamentor.springmvc.service.RoleService;
import ru.javamentor.springmvc.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
        return "admin/user";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("user") User user, ModelMap modelMap) {
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("listRoles", roleService.getAllUsers());
        return "admin/create";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                        ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "admin/create";
        } else {
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("listRoles", roleService.getAllUsers());
            userService.addUser(user);
            return "redirect:/admin/hello";
        }
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listRoles", roleService.getAllUsers());
        return "admin/edit";
    }
    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        } else {
            userService.updateUser(user);
            return "redirect:/admin/hello";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin/hello";
    }
}
