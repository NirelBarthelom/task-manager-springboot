package com.nzephan.taskmanager.controller;

import com.nzephan.taskmanager.entity.Comment;
import com.nzephan.taskmanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Create a new comment
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestParam Long taskId,
                                                 @RequestParam Long commenterId,
                                                 @RequestParam String commentText) {
        Comment savedComment = commentService.createComment(taskId, commenterId, commentText);
        return ResponseEntity.ok(savedComment);
    }

    // Get all comments for a specific task
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Comment>> getCommentsForTask(@PathVariable Long taskId) {
        List<Comment> comments = commentService.getCommentsForTask(taskId);
        return ResponseEntity.ok(comments);
    }
}
