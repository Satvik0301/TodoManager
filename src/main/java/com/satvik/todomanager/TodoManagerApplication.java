package com.satvik.todomanager;

import com.satvik.todomanager.dao.TodoDao;
import com.satvik.todomanager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {

    @Autowired
    TodoDao todoDao;

    Logger logger = LoggerFactory.getLogger(TodoManagerApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(TodoManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Application started");
//        JdbcTemplate template = todoDao.getTemplate();
//        logger.info("Template Object: {}", template);
//        logger.info("Template Object: {}", template);
//

//        Todo todo = new Todo();
//        todo.setId(1234);
//        todo.setTitle("This is testing Java");
//        todo.setContent("Its working");
//        todo.setStatus("Done");
//        todo.setCreated(new Date());
//        todo.setDueDate(new Date());
//        todoDao.saveTodo(todo);

//        Todo todo = todoDao.getTodo(123);
//        logger.info("Todo : {}",todo);

        List<Todo> allTodos = todoDao.getAllTodos();
        logger.info("Todos: {}",allTodos);
    }
}
