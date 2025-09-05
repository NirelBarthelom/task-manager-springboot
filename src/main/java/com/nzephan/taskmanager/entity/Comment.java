package com.nzephan.taskmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    // Reference to Task
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    // Reference to User (commenter)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commenter_id", nullable = false)
    private User commenter;

    @Column(name = "comment", length = 1000, nullable = false)
    private String commentText;

    @Column(name = "comment_created_date", nullable = false, updatable = false)
    private LocalDateTime commentCreatedDate;

    // Default constructor
    public Comment() {}

    // Constructor
    public Comment(Task task, User commenter, String commentText) {
        this.task = task;
        this.commenter = commenter;
        this.commentText = commentText;
        this.commentCreatedDate = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getCommentId() { return commentId; }
    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }
    public User getCommenter() { return commenter; }
    public void setCommenter(User commenter) { this.commenter = commenter; }
    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }
    public LocalDateTime getCommentCreatedDate() { return commentCreatedDate; }
    public void setCommentCreatedDate(LocalDateTime commentCreatedDate) { this.commentCreatedDate = commentCreatedDate; }
}
