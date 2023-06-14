package com.semenov.taskmanager.controller;

import com.semenov.taskmanager.model.User;
import com.semenov.taskmanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "/auth/registration";
    }

    // Регистрация пользователя
    @PostMapping("/registration")
    public String createNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (!userService.createUser(user)) {
            // случай, если такой пользователь уже существует:
            model.addAttribute("errorMessage", "Пользователь с таким логином уже существует");
            return "/auth/registration";

        } else if (bindingResult.hasErrors()) {
            return "/auth/registration";

        } else return "/user/index";
    }

}
