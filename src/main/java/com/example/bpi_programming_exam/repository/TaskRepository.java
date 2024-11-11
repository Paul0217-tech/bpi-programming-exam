package com.example.bpi_programming_exam.repository;

import com.example.bpi_programming_exam.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom query to check if at least one record exists (LIMIT 1)
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Task t")
    boolean isTableNotEmpty();
}
