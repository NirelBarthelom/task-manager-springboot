package com.nzephan.taskmanager.repository;

import com.nzephan.taskmanager.entity.Comment;
import com.nzephan.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Find all comments for a specific task
    List<Comment> findByTask(Task task);
}
