package com.nzephan.taskmanager.repository;

import com.nzephan.taskmanager.entity.Task;
import com.nzephan.taskmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Find tasks assigned to a particular User
    List<Task> findByAssignee(User assignee);

    // Optional: Find tasks requested by a particular User
    List<Task> findByRequester(User requester);
}
