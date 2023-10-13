package ru.javamentor.springmvc.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.springmvc.model.User;


@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/")
    public String show(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        return "user/user_page";
    }


}
