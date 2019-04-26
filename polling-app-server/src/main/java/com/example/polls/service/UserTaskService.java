package com.example.polls.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.polls.exception.ResourceNotFoundException;
import com.example.polls.model.Status;
import com.example.polls.model.Task;
import com.example.polls.model.User;
import com.example.polls.model.UserTask;
import com.example.polls.payload.PageResponse;
import com.example.polls.payload.UserTaskRequest;
import com.example.polls.payload.UserTaskResponse;
import com.example.polls.repository.TaskRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.repository.UserTaskRepository;
@Service
public class UserTaskService {
	 @Autowired
	  private TaskRepository taskRepository;
	 @Autowired
	  private UserRepository userRepository;
	 @Autowired
	  private UserTaskRepository userTaskRepository;

	public void createUserTask(Long userId, UserTaskRequest userTaskRequest) {
		User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
		Task task = taskRepository.findById(userTaskRequest.getTaskId()).orElseThrow(
                () -> new ResourceNotFoundException("Task", "id", userTaskRequest.getTaskId()));
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		UserTask userTask = new UserTask();
		
		
		try {
			userTask.setStartDate(transFormat.parse(userTaskRequest.getStartDate()));
			userTask.setEndDate(transFormat.parse(userTaskRequest.getEndDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		userTask.setTask(task);
		userTask.setUser(user);
		userTask.setStatus(Status.PROGRESS);
		userTaskRepository.save(userTask);
		
		
	}
	
	public List<UserTask> getAllUserTaskNoPage(Long userId) {
		return userTaskRepository.findAllByUserId(userId);
	}
	public PageResponse<UserTask> getAllUserTask(int page, int size) {
		  
		  Pageable pageable = PageRequest.of(page, size, new Sort(Direction.DESC, "createdAt"));
		  Page<UserTask> userTaskPage = userTaskRepository.findByStatus(Status.PROGRESS,pageable);
		  PageResponse<UserTask> pageResponse = pageConstructor(userTaskPage);
		  if(userTaskPage.getNumberOfElements() == 0) {
			   pageResponse.setContent(Collections.emptyList());
	       }else {
	    	   pageResponse.setContent(userTaskPage.getContent());
	       }
		  
		  return pageResponse;
	  }

	public PageResponse<UserTask> getUserTask(Long userId,int page, int size) {
		User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
		 Pageable pageable = PageRequest.of(page, size, new Sort(Direction.DESC, "createdAt"));
		  Page<UserTask> userTaskPage = userTaskRepository.findByStatusAndUser(Status.PROGRESS,user,pageable);
		  PageResponse<UserTask> pageResponse = pageConstructor(userTaskPage);
		  if(userTaskPage.getNumberOfElements() == 0) {
			   pageResponse.setContent(Collections.emptyList());
	       }else {
	    	   pageResponse.setContent(userTaskPage.getContent());
	       }
		  
		  return pageResponse;
	}

	public void deleteTask( UserTask userTaskRequest) {
		UserTask userTask = userTaskRepository.findById(userTaskRequest.getId()).orElseThrow(
	                () -> new ResourceNotFoundException("Task", "id", userTaskRequest.getId()));
		 userTask.setStatus(Status.END);
		  userTaskRepository.save(userTask);
	}



	private PageResponse<UserTask> pageConstructor(Page<UserTask> taskPage) {
	 PageResponse<UserTask> pageResponse = new PageResponse<UserTask>(  
	          taskPage.getNumber(),                  
	          taskPage.getSize(),           
			  taskPage.getTotalPages(),               
			  taskPage.getNumberOfElements(),  
			  taskPage.getTotalElements(),        
			  taskPage.hasPrevious(),  
			  taskPage.isFirst(),            
			  taskPage.hasNext(),           
			  taskPage.isLast(),              
			  taskPage.nextPageable(),       
			  taskPage.previousPageable(),          
			  taskPage.getSort()
			  );
			  
	
	 return pageResponse;
	 
	}
	

	



}




