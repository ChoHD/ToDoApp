package com.codestates.ToDoApp.service;

import com.codestates.ToDoApp.entity.TodoApp;
import com.codestates.ToDoApp.exception.BusinessLogicException;
import com.codestates.ToDoApp.exception.ExceptionCode;
import com.codestates.ToDoApp.reposittory.TodoAppRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TodoAppService {
    private final TodoAppRepository todoAppRepository;


    public TodoAppService(TodoAppRepository todoAppRepository) {
        this.todoAppRepository = todoAppRepository;

    }

    public TodoApp createTodoApp(TodoApp toDoApp) {
        verifyExistsTitle(toDoApp.getTitle());
        TodoApp savedTodoApp = todoAppRepository.save(toDoApp);

        return todoAppRepository.save(toDoApp);
    }
    public TodoApp updateTodoApp(TodoApp todoApp) {
        Optional<TodoApp> findTodoApp = todoAppRepository.findById(todoApp.getTodoappId());

       Optional.ofNullable(todoApp.getTitle()).ifPresent(title -> findTodoApp.get().setTitle(title));
       Optional.ofNullable(todoApp.getTodoorder()).ifPresent(todoorder -> findTodoApp.get().setTodoorder(todoorder));
       Optional.ofNullable(todoApp.getCompleted()).ifPresent(comepleted -> findTodoApp.get().setCompleted(comepleted));

       return todoAppRepository.save(findTodoApp.get());
    }

    public TodoApp readTodoApp(long todoappId) {
        return findVerifiedTodoApp(todoappId);
    }
    public List<TodoApp> readTodoApps() {

        List<TodoApp> todoApps = todoAppRepository.findAll();

        return todoApps;
    }
    public void deleteTodoApp(long todoappId) {
        TodoApp findTodoApp = findVerifiedTodoApp(todoappId);

        todoAppRepository.delete(findTodoApp);
    }
    @Transactional(readOnly = true)
    public TodoApp findVerifiedTodoApp(long todoappId) {
       Optional<TodoApp> optionalTodoApp = todoAppRepository.findById(todoappId);
       TodoApp findTodoApp = optionalTodoApp.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TITLE_NOT_FOUND));

       return findTodoApp;
    }
    private void verifyExistsTitle(String tilte) {
        Optional<TodoApp> todoApp = todoAppRepository.findByTitle(tilte);
        if (todoApp .isPresent())
            throw new BusinessLogicException(ExceptionCode.TITLE_NOT_FOUND);
    }
}
