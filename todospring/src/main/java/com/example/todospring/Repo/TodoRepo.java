package com.example.todospring.Repo;

import com.example.todospring.Entity.TodoTasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepo extends JpaRepository<TodoTasks,Long> {


    Optional<TodoTasks> findByUsersIdAndId(Long userId, Long id);

    Page<TodoTasks> findByUsersId(Long userid, Pageable pageable);
}
