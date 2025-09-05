package com.nzephan.taskmanager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Import Statements from here are needed to test backend
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

import com.nzephan.taskmanager.service.*;
import com.nzephan.taskmanager.entity.*;



@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	//Test Inserting Data into DB
//	@Bean
//	CommandLineRunner test(UserService userService, TaskService taskService, CommentService commentService) {
//		return args -> {
//			// 1. Create a user
//			User requester = new User("alice", "alice@example.com", "pass123", "USER");
//			requester = userService.saveUser(requester);
//
//			User assignee = new User("bob", "bob@example.com", "pass456", "USER");
//			assignee = userService.saveUser(assignee);
//
//			// 2. Create a task
//			Task task = new Task("Finish backend", "Implement services and controllers",
//					Task.Status.Open, requester, assignee, LocalDate.now().plusDays(7));
//			task = taskService.createTask(task, requester.getId(), assignee.getId());
//
//			// 3. Add a comment
//			Comment comment = commentService.createComment(task.getTaskId(), requester.getId(), "This needs to be done ASAP!");
//
//			System.out.println("Inserted test data:");
//			System.out.println("User IDs: " + requester.getId() + ", " + assignee.getId());
//			System.out.println("Task ID: " + task.getTaskId());
//			System.out.println("Comment ID: " + comment.getCommentId());
//		};
//	}


}


