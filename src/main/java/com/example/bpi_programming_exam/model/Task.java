package com.example.bpi_programming_exam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int duration;  // Duration in days
    private String startDate;
    private String endDate;

    @ManyToMany
    @JoinTable(
            name = "task_dependencies",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "dependency_id"))
    private List<Task> dependencies;

    // Method to calculate start and end dates
    public void  calculateDates(LocalDate projectStartDate, Task task) {
        // If there are no dependencies, start from project start date
        LocalDate start = projectStartDate;

        if (task.dependencies != null) {
            // If there are dependencies, start after the latest dependency end date
            for (Task dependency : task.dependencies) {
                if (dependency.getEndDate() != null) {
                    LocalDate dependencyEndDate = LocalDate.parse(dependency.getEndDate());
                    if (dependencyEndDate.isAfter(start)) {
                        start = dependencyEndDate;
                    }
                }
            }
        }

        this.startDate = start.toString();
        this.endDate = start.plusDays(this.duration).toString();
    }
}