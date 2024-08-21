package com.example.todospring.Controler;


import com.example.todospring.Entity.TodoTasks;
import com.example.todospring.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoContro {

    @Autowired
    private TodoService todoService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello thr ";
    }

    @PostMapping("/Todo")
    public ResponseEntity<TodoTasks>savetodo(@RequestBody TodoTasks todoTasks){
        return ResponseEntity.ok( todoService.savetodo(todoTasks));
    }

    @GetMapping("/Todo")
    public List<TodoTasks> alltodo(Pageable pageable){
        return todoService.gettodobyuserid(pageable).toList();
    }

    @PutMapping("/Todo/{id}")
    public TodoTasks updatetodo(@RequestBody TodoTasks todoTasks,@PathVariable Long id){
        return todoService.updatetask(id, todoTasks);
    }
}
