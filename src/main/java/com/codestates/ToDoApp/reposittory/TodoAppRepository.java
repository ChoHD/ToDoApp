package com.codestates.ToDoApp.reposittory;

import com.codestates.ToDoApp.entity.TodoApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoAppRepository extends JpaRepository<TodoApp, Long> {
        Optional<TodoApp> findByTitle(String title);
}
