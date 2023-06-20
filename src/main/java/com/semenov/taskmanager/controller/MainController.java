package com.semenov.taskmanager.controller;

import com.semenov.taskmanager.model.Task;
import com.semenov.taskmanager.repository.TaskRepository;
import com.semenov.taskmanager.service.TaskService;
import com.semenov.taskmanager.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class MainController {

    private final UserService userService;
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public MainController(UserService userService, TaskService taskService, TaskRepository taskRepository) {
        this.userService = userService;
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/index")
    public String index(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUser(authentication));
        return "/user/index";
    }

    @GetMapping("/party")
    public String getParty(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "/user/party";
    }

    @GetMapping("/task-list")
    public String taskList(Model model) {
        model.addAttribute("taskList", taskRepository.findAll());
        return "/user/task-list";
    }

    @GetMapping("/task-form")
    public String createNewTask(Model model) {
        model.addAttribute("task", new Task());
        return "/user/task-form";
    }

    @PostMapping("/task-form")
    public String createNewTask(@ModelAttribute("task") Task task, Model model) {
        taskService.createTask(task);
        return "redirect:/user/task-list";
    }

}
