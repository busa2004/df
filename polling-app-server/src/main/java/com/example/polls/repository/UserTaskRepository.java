package com.example.polls.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.polls.model.Status;
import com.example.polls.model.Task;
import com.example.polls.model.User;
import com.example.polls.model.UserTask;
import com.example.polls.payload.PageResponse;
@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
	 @Query("SELECT v FROM UserTask v WHERE v.user.id = :userId")
	List<UserTask> findAllByUserId(@Param("userId") Long userId);
	 
	 
	 Page<UserTask> findByStatus(Status progress, Pageable Pageable);


	 Page<UserTask> findByStatusAndUser(Status progress, User user, Pageable pageable);


	List<UserTask> findByUser(User user);

	 @Query("SELECT v FROM UserTask v WHERE v.user.id = :userId")
	List<UserTask> findByUserId(Long userId);


	

}
