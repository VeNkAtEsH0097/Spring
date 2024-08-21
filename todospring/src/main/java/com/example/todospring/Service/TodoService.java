package com.example.todospring.Service;

import com.example.todospring.Entity.TodoTasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface TodoService  {

    TodoTasks savetodo(TodoTasks todoTasks);

    Page<TodoTasks> gettodobyuserid( Pageable pageable);

    TodoTasks updatetask(Long id,TodoTasks todoTasks);

    TodoTasks gettodobyid(Long id);


}
