package com.example.bpi_programming_exam.controller;

import com.example.bpi_programming_exam.model.Task;
import com.example.bpi_programming_exam.repository.TaskRepository;
import com.example.bpi_programming_exam.service.TaskSchedulerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskSchedulerService taskSchedulerService;
    private final TaskRepository taskRepository;

    public TaskController(TaskSchedulerService taskSchedulerService,
                          TaskRepository taskRepository) {
        this.taskSchedulerService = taskSchedulerService;
        this.taskRepository = taskRepository;
    }

    // Endpoint to schedule all tasks
    @PostMapping("/schedule")
    public String scheduleTasks(@RequestBody List<Task> tasks) {
        taskSchedulerService.scheduleAllTasks(tasks);
        return "Tasks have been scheduled successfully!";
    }

    // Endpoint to get all tasks and their schedules
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskSchedulerService.getAllTasks();
    }
}
