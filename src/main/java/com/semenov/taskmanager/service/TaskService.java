package com.semenov.taskmanager.service;

import com.semenov.taskmanager.model.Task;
import com.semenov.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }
}
