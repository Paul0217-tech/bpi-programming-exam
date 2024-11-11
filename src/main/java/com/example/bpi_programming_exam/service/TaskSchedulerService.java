package com.example.bpi_programming_exam.service;

import com.example.bpi_programming_exam.model.Task;
import com.example.bpi_programming_exam.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TaskSchedulerService {

    private final TaskRepository taskRepository;

    public TaskSchedulerService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Schedule all tasks based on their dependencies
    @Transactional
    public void scheduleAllTasks(List<Task> tasks) {
        // Get the project start date (e.g., today or any other default start date)
        LocalDate projectStartDate = LocalDate.now();  // You can change this to a configurable start date

        // Calculate the start and end dates for each task
        for (Task task : tasks) {
            // check for first task.
            if (!taskRepository.isTableNotEmpty()) {
                if (task.getStartDate() == null || task.getStartDate().isEmpty()){
                    task.setStartDate(String.valueOf(projectStartDate));
                }
                task.setEndDate(String.valueOf(LocalDate.parse(task.getStartDate()).plusDays(task.getDuration())));

                // dependency should be empty at first input.
                if (!task.getDependencies().isEmpty()) {
                    log.error("dependencies field should be empty for the first input.");
                    break;
                }
                taskRepository.save(task);
            } else {
                // set dependencies base on ID
                List<Task> listDependencies = new ArrayList<>();
                for (Task dependency : task.getDependencies()) {
                    listDependencies.add(taskRepository.findById(dependency.getId()).stream().toList().get(0));
                }
                task.setDependencies(listDependencies);
                task.calculateDates(projectStartDate, task);
                taskRepository.save(task);  // Save updated task with calculated dates
            }
        }
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
