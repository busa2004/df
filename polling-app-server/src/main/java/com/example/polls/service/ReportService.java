package com.example.polls.service;

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
import com.example.polls.model.Report;
import com.example.polls.model.Status;
import com.example.polls.model.Task;
import com.example.polls.model.User;
import com.example.polls.model.UserTask;
import com.example.polls.payload.PageResponse;
import com.example.polls.payload.ReportRequest;
import com.example.polls.repository.ReportRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.repository.UserTaskRepository;

@Service
public class ReportService {
	 @Autowired
	  private UserTaskRepository userTaskRepository;

	   @Autowired
	   private ReportRepository reportRepository;
	
	   @Autowired
		  private UserRepository userRepository;

	public void createReport(ReportRequest reportRequest) {
		 Report report = new Report();
		 report.setTitle(reportRequest.getTitle());
		 report.setContent(reportRequest.getContent());
		 UserTask userTask = userTaskRepository.findById(reportRequest.getUserTaskId()).orElseThrow(
	                () -> new ResourceNotFoundException("report", "userTaskId", reportRequest.getUserTaskId()));
		 
		 report.setUserTask(userTask);
		 report.setStatus(Status.PROGRESS);
		 reportRepository.save(report);
	}

	public List<Report> getAllReport(Long userId) {
		  
	
		  List<Report> reportPage = reportRepository.findByStatusAndUserId(Status.END,userId);
		  
		  
		  return reportPage;
	  }
	
	
	public PageResponse<Report> getReportByUserTask(Long userId,int page, int size) {
		 Pageable pageable = PageRequest.of(page, size, new Sort(Direction.DESC, "createdAt"));
		  Page<Report> reportPage = reportRepository.findByStatusAndUserId(Status.PROGRESS,userId,pageable);
		  PageResponse<Report> pageResponse = pageConstructor(reportPage);
		  if(reportPage.getNumberOfElements() == 0) {
			   pageResponse.setContent(Collections.emptyList());
	       }else {
	    	   pageResponse.setContent(reportPage.getContent());
	       }
		  
		  return pageResponse;
	}




	public void deleteTask(@Valid Report reportRequest) {
		Report report = reportRepository.findById(reportRequest.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Task", "id", reportRequest.getId()));
		report.setStatus(Status.END);
		reportRepository.save(report);
	}

	
	
	private PageResponse<Report> pageConstructor(Page<Report> taskPage) {
		 PageResponse<Report> pageResponse = new PageResponse<Report>(  
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
