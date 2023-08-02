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

import java.util.Optional;

@Transactional
@Service
public class TodoAppService {
    private final TodoAppRepository todoAppRepository;


    public TodoAppService(TodoAppRepository todoAppRepository) {

        this.todoAppRepository = todoAppRepository;

    }

    public TodoApp createTodoApp(TodoApp toDoApp) {

        TodoApp savedTodoApp = todoAppRepository.save(toDoApp);

        return todoAppRepository.save(toDoApp);
    }
    public TodoApp updateTodoApp(TodoApp todoApp) {

        TodoApp updatetodoapp = todoAppRepository.getReferenceById(todoApp.getId());

        Optional.ofNullable(todoApp.getTitle())
                .ifPresent(title -> updatetodoapp.setTitle(title));
        Optional.ofNullable(todoApp.getTodoorder())
                .ifPresent(todoorder -> updatetodoapp.setTodoorder(todoorder));


        return todoAppRepository.save(updatetodoapp);
    }

    public TodoApp readTodoApp(long todoappId) {
        return findVerifiedTodoApp(todoappId);
    }
    public Page<TodoApp> readTodoApps(int page, int size) {
        return todoAppRepository.findAll(PageRequest.of(page, size, Sort.by("todoappId").descending()));
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
        if (todoApp.isPresent())
            throw new BusinessLogicException(ExceptionCode.TITLE_NOT_FOUND);
    }
}
