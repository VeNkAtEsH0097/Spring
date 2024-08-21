package com.example.todospring.Service;


import com.example.todospring.Entity.TodoTasks;
import com.example.todospring.Repo.TodoRepo;
import com.example.todospring.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Todoimpl implements TodoService{

    @Autowired
    TodoRepo todoRepo;

    @Autowired
    UserService userService;

    @Override
    public TodoTasks savetodo(TodoTasks todoTasks) {

        todoTasks.setUsers(userService.Getloggedinuser());
        return todoRepo.save(todoTasks);

    }

    @Override
    public Page<TodoTasks> gettodobyuserid(Pageable pageable) {
        return todoRepo.findByUsersId(userService.Getloggedinuser().getId(),pageable);
    }

    @Override
    public TodoTasks updatetask(Long id, TodoTasks todoTasks) {
        TodoTasks newtodo = gettodobyid(id);
        newtodo.setTasks(todoTasks.getTasks());
        return todoRepo.save(newtodo);
    }

    @Override
    public TodoTasks gettodobyid(Long id) {
        Optional<TodoTasks> todoTasks = todoRepo.findByUsersIdAndId(userService.Getloggedinuser().getId(),id);
        if(todoTasks.isPresent()){
            return todoTasks.get();
        }
        throw  new ResourceNotFoundException("Todo not found");
    }

}
