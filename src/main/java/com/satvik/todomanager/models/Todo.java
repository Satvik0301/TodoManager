package com.satvik.todomanager.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Todo {
    private int id;
    private String title;
    private String content;
    private String status;
    private Date created;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    public Todo(int id, String title, String content, String status, Date created, Date dueDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.created = created;
        this.dueDate = dueDate;
    }

    public Todo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", created=" + created +
                ", dueDate=" + dueDate +
                '}';
    }
}
