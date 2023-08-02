package com.codestates.ToDoApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class TodoApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;

    private int todoorder;

    private boolean completed;

    public TodoApp(String title) {
        this.title = title;
    }
    public TodoApp(String title, int todoorder, boolean completed) {
        this.title = title;
        this.todoorder = todoorder;
        this.completed = completed;
    }
}
