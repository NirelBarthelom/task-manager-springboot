package com.nzephan.taskmanager.controller;

import com.nzephan.taskmanager.entity.Task;
import com.nzephan.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Create a new task
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task,
                                           @RequestParam Long requesterId,
                                           @RequestParam(required = false) Long assigneeId) {
        Task savedTask = taskService.createTask(task, requesterId, assigneeId);
        return ResponseEntity.ok(savedTask);
    }

    // Get tasks assigned to a user
    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<Task>> getTasksForUser(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksForUser(userId);
        return ResponseEntity.ok(tasks);
    }

    // Update an existing task
    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.updateTask(task);
        return ResponseEntity.ok(updatedTask);
    }
}
