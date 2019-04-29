package com.example.polls.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.polls.model.Report;
import com.example.polls.model.Status;
import com.example.polls.model.User;
import com.example.polls.model.UserTask;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	 @Query("SELECT v FROM Report v WHERE v.userTask.user.id = :userId")
	List<Report> findByUserId(@Param("userId") Long userId);

	Page<Report> findByStatus(Status progress, Pageable pageable);
	
	Page<Report> findByStatusAndUserTask(Status progress, List<UserTask> userTask, Pageable pageable);
	 @Query("SELECT v FROM Report v WHERE v.userTask.user.id = :userId and v.status = :progress")
	Page<Report> findByStatusAndUserId(@Param("progress") Status progress,@Param("userId") Long userId, Pageable pageable);

	List<Report> findByStatus(Status progress);
	
	
	 @Query("SELECT v FROM Report v WHERE v.userTask.user.id = :userId and v.status != :progress")
	List<Report> findByStatusAndUserId(@Param("progress") Status progress,@Param("userId") Long userId);
	


}
