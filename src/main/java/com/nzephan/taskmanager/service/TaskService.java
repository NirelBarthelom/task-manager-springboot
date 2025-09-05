package com.nzephan.taskmanager.service;

import com.nzephan.taskmanager.entity.Task;
import com.nzephan.taskmanager.entity.User;
import com.nzephan.taskmanager.repository.TaskRepository;
import com.nzephan.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository; // needed to fetch User objects

    // Creates a new task
    public Task createTask(Task requestTask, Long requesterId, Long assigneeId) {
        Task.Status status = requestTask.getStatus();
        if (status == null) {
            status = Task.Status.Open;  // keep original logic
        }

        // Fetch the requester
        User requester = userRepository.findById(requesterId).orElse(null);
        if (requester == null) {
            throw new IllegalArgumentException("Requester not found with id: " + requesterId);
        }

        // Fetch the assignee (if provided)
        User assignee = null;
        if (assigneeId != null) {
            assignee = userRepository.findById(assigneeId).orElse(null);
            if (assignee == null) {
                throw new IllegalArgumentException("Assignee not found with id: " + assigneeId);
            }
        }

        Task newTask = new Task(
                requestTask.getTitle(),
                requestTask.getDescription(),
                status,
                requester,
                assignee,
                requestTask.getTargetDate()
        );

        return taskRepository.save(newTask);
    }

    // Returns all tasks assigned to a particular user
    public List<Task> getTasksForUser(Long assigneeId) {
        User assignee = userRepository.findById(assigneeId).orElse(null);
        if (assignee == null) {
            throw new IllegalArgumentException("User not found with id: " + assigneeId);
        }

        return taskRepository.findByAssignee(assignee);  // updated to use User instead of Long
    }

    // Updates an existing task
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
}
