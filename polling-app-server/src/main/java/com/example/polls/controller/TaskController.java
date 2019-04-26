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
import com.example.polls.payload.PageResponse;
import com.example.polls.payload.PagedResponse;
import com.example.polls.payload.PollResponse;
import com.example.polls.service.TaskService;
import com.example.polls.util.AppConstants;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	 @Autowired
	 private TaskService TaskService;
	 	
	 	@GetMapping("/create")
	    public void createTask(@Valid @RequestBody Task taskRequest) {
	        TaskService.createTask(taskRequest);
	    }
	 	
	 	@GetMapping
	    public PageResponse<Task> getAllTask(
	    		@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
	    		@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
	        return TaskService.getAllTask(page,size);
	    }
	 	
	 	@GetMapping("/update")
	    public void updateTask(@Valid @RequestBody Task taskRequest) {
	        TaskService.updateTask(taskRequest);
	    }
	 	
	 	@GetMapping("/delete")
	    public void deleteTask(@Valid @RequestBody Task taskRequest) {
	        TaskService.deleteTask(taskRequest);
	    }
	 	
//	 	@GetMapping
//	    public List<Task> getAllTask( 
//	    		@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//	        return TaskService.getAllTask(page,size);
//	    }
	 	
}