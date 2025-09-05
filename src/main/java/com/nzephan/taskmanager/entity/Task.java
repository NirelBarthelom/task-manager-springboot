package com.nzephan.taskmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")  // snake_case for DB
    private Long taskId;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.Open;  // default

    // Requester (user who created the task)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    // Assignee (user assigned to complete the task)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Column(name = "target_date")
    private LocalDate targetDate;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    // Default constructor
    public Task() {}

    // Constructor without taskId (auto-generated)
    public Task(String title, String description, Status status,
                User requester, User assignee, LocalDate targetDate) {
        this.title = title;
        this.description = description;

        // Keep original if statement for status
        if (status != null) {
            this.status = status;
        } else {
            this.status = Status.Open;
        }

        this.requester = requester;
        this.assignee = assignee;
        this.targetDate = targetDate;
        this.createdDate = LocalDateTime.now();
    }

    // Enum for status
    public enum Status {
        Open,
        In_Progress,
        Completed
    }

    // Getters & Setters
    public Long getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public User getRequester() { return requester; }
    public void setRequester(User requester) { this.requester = requester; }

    public User getAssignee() { return assignee; }
    public void setAssignee(User assignee) { this.assignee = assignee; }

    public LocalDate getTargetDate() { return targetDate; }
    public void setTargetDate(LocalDate targetDate) { this.targetDate = targetDate; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
}
