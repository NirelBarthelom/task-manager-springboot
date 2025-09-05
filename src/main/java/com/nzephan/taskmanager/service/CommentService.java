package com.nzephan.taskmanager.service;

import com.nzephan.taskmanager.entity.Comment;
import com.nzephan.taskmanager.entity.Task;
import com.nzephan.taskmanager.entity.User;
import com.nzephan.taskmanager.repository.CommentRepository;
import com.nzephan.taskmanager.repository.TaskRepository;
import com.nzephan.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new comment
    public Comment createComment(Long taskId, Long commenterId, String commentText) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            throw new IllegalArgumentException("Task not found with id: " + taskId);
        }

        User commenter = userRepository.findById(commenterId).orElse(null);
        if (commenter == null) {
            throw new IllegalArgumentException("User not found with id: " + commenterId);
        }

        Comment comment = new Comment(task, commenter, commentText);
        return commentRepository.save(comment);
    }

    // Get all comments for a task
    public List<Comment> getCommentsForTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            throw new IllegalArgumentException("Task not found with id: " + taskId);
        }

        return commentRepository.findByTask(task);
    }
}
