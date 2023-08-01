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
    private Long todoappId;


    private String title;


    private int todoorder;


    private boolean cpmpleted;

    public TodoApp(String title) {
        this.title = title;
    }
}
