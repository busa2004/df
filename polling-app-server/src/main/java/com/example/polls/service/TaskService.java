package com.example.polls.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.polls.exception.BadRequestException;
import com.example.polls.exception.ResourceNotFoundException;
import com.example.polls.model.Status;
import com.example.polls.model.Task;
import com.example.polls.payload.PageResponse;
import com.example.polls.payload.PagedResponse;
import com.example.polls.repository.TaskRepository;
import com.example.polls.util.AppConstants;

@Service
public class TaskService {
	  @Autowired
	  private TaskRepository taskRepository;
	  
	  public void createTask(Task taskRequest) {
		  Task task = new Task();
		  task.setTitle(taskRequest.getTitle());
		  task.setContent(taskRequest.getContent());
		  task.setStatus(Status.PROGRESS);
		  taskRepository.save(task);
	  }
	  
	public PageResponse<Task> getAllTask(int page, int size) {
		  
		  Pageable pageable = PageRequest.of(page, size, new Sort(Direction.DESC, "createdAt"));
		  Page<Task> taskPage = taskRepository.findByStatus(Status.PROGRESS,pageable);
		  PageResponse<Task> pageResponse = pageConstructor(taskPage);
		  if(taskPage.getNumberOfElements() == 0) {
			   pageResponse.setContent(Collections.emptyList());
	       }else {
	    	   pageResponse.setContent(taskPage.getContent());
	       }
		  
		  return pageResponse;
	  }

	public void deleteTask(Task taskRequest) {
		  Task task = taskRepository.findById(taskRequest.getId()).orElseThrow(
	                () -> new ResourceNotFoundException("Task", "id", taskRequest.getId()));
		  task.setStatus(Status.END);
		  taskRepository.save(task);
	  }

	public void updateTask(Task taskRequest) {
		Task task = taskRepository.findById(taskRequest.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Task", "id", taskRequest.getId()));
		  task.setTitle(taskRequest.getTitle());
		  task.setContent(taskRequest.getContent());
		taskRepository.save(task);
		
	}


	
	
	
	
	
	
	
	
	
	
	 private PageResponse<Task> pageConstructor(Page<Task> taskPage) {
		 PageResponse<Task> pageResponse = new PageResponse<Task>(  
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
