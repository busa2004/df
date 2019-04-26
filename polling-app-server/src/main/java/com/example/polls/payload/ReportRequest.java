package com.example.polls.payload;

public class ReportRequest {
	private String title;
	private String content;
	private Long userTaskId;
	
	
	public Long getUserTaskId() {
		return userTaskId;
	}
	public void setUserTaskId(Long userTaskId) {
		this.userTaskId = userTaskId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
