package com.example.polls.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.polls.model.Status;
import com.example.polls.model.Task;
import com.example.polls.model.UserTask;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	

	 Page<Task> findByStatus(Status progress, Pageable Pageable);

	





	List<Task> findByStatus(Status progress);


}
