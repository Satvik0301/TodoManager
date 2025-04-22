package com.satvik.todomanager.dao;

import com.satvik.todomanager.helper.Helper;
import com.satvik.todomanager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);

    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        //create table if not exists
        String createTable = "create table if not exists todos(id int primary key,title varchar(100) not null,content varchar(500),status varchar(10) not null,created datetime,dueDate datetime)";
        int update = template.update(createTable);
        logger.info("TODO TABLE CREATED: {}",update);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //save todo in database
    public Todo saveTodo(Todo todo){

        String insertQuery = "insert into todos(id,title,content,status,created,dueDate) values(?,?,?,?,?,?)";
        int rows = template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getCreated(), todo.getDueDate());
        logger.info("JDBC Operation: {} inserted",rows);
        return todo;
    }

    //get todo from database
    //incomplete
    public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos where id = ?";
        Map<String, Object> todoData = template.queryForMap(query, id);


        Todo todo = new Todo();

        todo.setId((Integer) todoData.get("id"));
        todo.setTitle((String)todoData.get("title"));
        todo.setContent((String)todoData.get("content"));
        todo.setStatus((String)todoData.get("status"));
        todo.setCreated(Helper.parseDate((LocalDateTime) todoData.get("created")));
        todo.setDueDate(Helper.parseDate((LocalDateTime) todoData.get("dueDate")));


        return todo;
    }

    //get all todos
    public List<Todo> getAllTodos(){
        String query = "select * from todos";
        List<Map<String, Object>> maps = template.queryForList(query);

        List<Todo> todos = maps.stream().map((map) -> {
            Todo todo = new Todo();

            todo.setId((Integer) map.get("id"));
            todo.setTitle((String) map.get("title"));
            todo.setContent((String) map.get("content"));
            todo.setStatus((String) map.get("status"));
            try {
                todo.setCreated(Helper.parseDate((LocalDateTime) map.get("created")));
                todo.setDueDate(Helper.parseDate((LocalDateTime) map.get("dueDate")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }



            return todo;
        }).collect(Collectors.toList());

        return todos;

    }

}
