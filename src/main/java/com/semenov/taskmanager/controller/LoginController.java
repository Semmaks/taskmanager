package com.semenov.taskmanager.controller;

import com.semenov.taskmanager.model.User;
import com.semenov.taskmanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/auth")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth/login")
    public String login() {
        return "/auth/login";
    }

    @GetMapping("/auth/registration")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/auth/registration";
    }

    // Регистрация пользователя
    @PostMapping("/auth/registration")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        // случай, если такой пользователь уже существует:
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с таким логином " +
                    user.getLogin() + " уже существует");
            return "/auth/registration";
        }
        return "/user/index";
    }

    @GetMapping("/user/index")
    public String index() {
        return "/user/index";
    }

}
