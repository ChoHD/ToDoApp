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
    private long todoappId;

    @Column(nullable = false, updatable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private int todoorder;

    @Column(nullable = false)
    private boolean cpmpleted;

    public TodoApp(String title) {
        this.title = title;
    }
}
