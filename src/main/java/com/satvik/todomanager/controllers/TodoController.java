package com.satvik.todomanager.controllers;

import com.satvik.todomanager.models.Todo;
import com.satvik.todomanager.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    Random random = new Random();

    //create
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
        //create todo

        //forcefull err
//        String str = null;
//        logger.info("length {}",str.length());

        //automatically set id
        int id = random.nextInt(9999999);
        todo.setId(id);

        //automatically set created Date
        Date currentDate = new Date();
        logger.info("Current date: " + currentDate);
        logger.info("Due date: " + todo.getDueDate() );
        todo.setCreated(currentDate);

        logger.info("Create Todo");
        //call service to create todo
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    //get all todo method
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos,HttpStatus.OK);
    }

    //get single todo
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId){
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    //update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo newTodo,@PathVariable int todoId){
        Todo todo = todoService.updateTodo(todoId,newTodo);
        return ResponseEntity.ok(todo);
    }

    //delete todo
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo Successfully Deleted");
    }

//    //exception handler
//    @ExceptionHandler(value={NullPointerException.class, NumberFormatException.class})
//    public ResponseEntity<String> ExceptionHandler(Exception ex){
//        System.out.println(ex.getMessage());
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }


}
