package com.example.polls.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.model.Task;
import com.example.polls.model.UserTask;
import com.example.polls.payload.PageResponse;
import com.example.polls.payload.UserTaskRequest;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.TaskService;
import com.example.polls.service.UserTaskService;
import com.example.polls.util.AppConstants;



@RestController
@RequestMapping("/api/usertask")
public class UserTaskController {
	 @Autowired
	 private UserTaskService UserTaskService;
	 	
	 	@GetMapping("/create")
	    public void createUserTask(@Valid @RequestBody UserTaskRequest userTaskRequest,
	    		@CurrentUser UserPrincipal currentUser) {
	 		UserTaskService.createUserTask(currentUser.getId(),userTaskRequest);
	    }
	 	
	 	@GetMapping("/allnopage")
	    public List<UserTask> getAllUserTaskNoPage(@CurrentUser UserPrincipal currentUser) {
	 		return  UserTaskService.getAllUserTaskNoPage(currentUser.getId());
	 	}
	 	
	 	
	 	@GetMapping("/all")
	    public PageResponse<UserTask> getAllUserTask(
	    		@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
	    		@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
	    		) {
	 		return  UserTaskService.getAllUserTask(page,size);
	 	}
	 	
	 	@GetMapping
	    public PageResponse<UserTask> getUserTask(@CurrentUser UserPrincipal currentUser,
	    		@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
	    		@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
	 		return  UserTaskService.getUserTask(currentUser.getId(),page,size);
	 	}
	 	
	 	@GetMapping("/delete")
	    public void deleteTask(@Valid @RequestBody UserTask userTaskRequest) {
	 		UserTaskService.deleteTask(userTaskRequest);
	    }
	 	
	 	
}
