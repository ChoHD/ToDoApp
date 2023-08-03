package com.codestates.ToDoApp.controller;


import com.codestates.ToDoApp.dto.TodoAppDto;
import com.codestates.ToDoApp.dto.TodoAppPatchDto;
import com.codestates.ToDoApp.dto.TodoAppPostDto;
import com.codestates.ToDoApp.dto.TodoAppResponseDto;
import com.codestates.ToDoApp.entity.TodoApp;
import com.codestates.ToDoApp.mapper.TodoAppMapper;
import com.codestates.ToDoApp.response.MultiResponseDto;
import com.codestates.ToDoApp.response.SingleResponseDto;
import com.codestates.ToDoApp.service.TodoAppService;
import com.codestates.ToDoApp.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping
public class TodoAppController {
    private final static String TODOAPP_DEFAULT_URL = "";
    private final TodoAppService toDoAppService;
    private final TodoAppMapper todoAppMapper;

    public TodoAppController(TodoAppService toDoAppService, TodoAppMapper todoAppMapper) {
        this.toDoAppService = toDoAppService;
        this.todoAppMapper = todoAppMapper;
    }
    @PostMapping
    public ResponseEntity postTodoapp(@Valid @RequestBody TodoAppDto.Post requestBody) {
        TodoApp todoApp = todoAppMapper.todoappPostToTodoApp(requestBody);

        TodoApp createTodoApp = toDoAppService.createTodoApp(todoApp);

        return new ResponseEntity<>(todoAppMapper.todoappToTodoAppResponse(createTodoApp), HttpStatus.CREATED);
    }

    @PatchMapping("/{todoapp-id}")
    public ResponseEntity patchTodoApp(
            @PathVariable("todoapp-id") @Positive long todoappId,
            @RequestBody TodoAppDto.Patch requestBody) {

        requestBody.setTodoAppId(todoappId);

        TodoApp todoApp =
                toDoAppService.updateTodoApp(todoAppMapper.todoappPatchToTodoApp(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(todoAppMapper.todoappToTodoAppResponse(todoApp)),HttpStatus.OK);
    }

    @GetMapping("/{todoapp-id}")
    public ResponseEntity readTodoApp(
            @PathVariable("todoapp-id") @Positive long todoappId) {
        TodoApp todoApp = toDoAppService.readTodoApp(todoappId);

       return new ResponseEntity<>(
               new SingleResponseDto<>(todoAppMapper.todoappToTodoAppResponse(todoApp)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity readTodoApps() {
        List<TodoApp> todoApps = toDoAppService.readTodoApps();
        return new ResponseEntity<>((todoApps), HttpStatus.OK);
    }
    @DeleteMapping("/{todoapp-id}")
    public ResponseEntity deleteTodoApp(@PathVariable("todoapp-id") @Positive long todoappId) {
        toDoAppService.deleteTodoApp(todoappId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
