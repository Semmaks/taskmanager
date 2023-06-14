package com.semenov.taskmanager.controller;

import com.semenov.taskmanager.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUser(authentication));
        return "/user/index";
    }
}
