package com.example.polls.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.model.Report;
import com.example.polls.model.Task;
import com.example.polls.model.UserTask;
import com.example.polls.payload.PageResponse;
import com.example.polls.payload.ReportRequest;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.ReportService;
import com.example.polls.util.AppConstants;

@RestController
@RequestMapping("/api/report")
public class ReportController {
	 @Autowired
	 private ReportService reportService;

	 	@GetMapping("/create")
	    public void createReport(@Valid @RequestBody ReportRequest reportRequest) {
	 		
	        reportService.createReport(reportRequest);
	        
	    }
	 	@GetMapping("/all")
	    public PageResponse<Report> getAllReport(
	    		@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
	    		@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
	        return reportService.getAllReport(page,size);
	    }
	 	@GetMapping
	    public PageResponse<Report> getReport(
	    		@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
	    		@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
	        return  reportService.getReportByUserTask(1L,page,size);
	    }
	 	@GetMapping("/delete")
	    public void deleteReport(@Valid @RequestBody Report reportRequest) {
	        reportService.deleteTask(reportRequest);
	    }
}
