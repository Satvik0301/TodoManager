package com.satvik.todomanager.services;

import com.satvik.todomanager.exceptions.ResourceNotFoundException;
import com.satvik.todomanager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@Service
public class TodoService {

    Logger logger= LoggerFactory.getLogger(TodoService.class);
    //fake database
    List<Todo> todos = new ArrayList<>();

    //get single todo method
    public Todo getTodo(int todoId){
        Todo todo = todos.stream().filter(t->todoId==t.getId()).findAny().orElseThrow(()-> new ResourceNotFoundException("Todo not found with given ID", HttpStatus.NOT_FOUND));
        logger.info("TODO: {}",todo);
        return todo;
    }

    //create todo method
    public Todo createTodo(Todo todo){
        //create...
        //change the logic with real database
        todos.add(todo);
        logger.info("Todos {}",this.todos);
        return todo;
    }

    //get all todos method
    public List<Todo> getAllTodos() {
        return todos;
    }

    //update todo method
    public Todo updateTodo(int todoId,Todo newTodo){
        List<Todo> newUpdatedList = todos.stream().map(t->{
            if(t.getId()==todoId){
                //perform update
                t.setTitle(newTodo.getTitle());
                t.setContent(newTodo.getContent());
                t.setStatus(newTodo.getStatus());
                return t;
            }else{
                return t;
            }
        }).collect(Collectors.toList());

        todos=newUpdatedList;
        newTodo.setId(todoId);
        return newTodo;
    }

    //delete todo method
    public void deleteTodo(int todoId){
        logger.info("DELETING TODO");
        List<Todo> newList = todos.stream().filter(t -> t.getId() != todoId).collect(Collectors.toList());
        todos=newList;
    }
}
