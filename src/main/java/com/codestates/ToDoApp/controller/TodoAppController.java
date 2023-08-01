package com.codestates.ToDoApp.controller;


import com.codestates.ToDoApp.dto.TodoAppDto;
import com.codestates.ToDoApp.dto.TodoAppPatchDto;
import com.codestates.ToDoApp.dto.TodoAppPostDto;
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
@RequestMapping("/v1/todoapps")
public class TodoAppController {
    private final static String TODOAPP_DEFAULT_URL = "/v1/todoapps";
    private final TodoAppService toDoAppService;
    private final TodoAppMapper todoAppMapper;

    public TodoAppController(TodoAppService toDoAppService, TodoAppMapper todoAppMapper) {
        this.toDoAppService = toDoAppService;
        this.todoAppMapper = todoAppMapper;
    }
    @PostMapping
    public ResponseEntity postTodoapp(@Valid @RequestBody TodoAppPostDto todoAppPostDto) {

        TodoApp todoApp = toDoAppService.createTodoApp(todoAppMapper.todoappPostDtoToTodoapp(todoAppPostDto));
        URI loaction = UriCreator.createUri(TODOAPP_DEFAULT_URL, todoApp.getTodoappId());

        return ResponseEntity.created(loaction).build();
    }

    @PatchMapping("/{todoapp-id}")
    public ResponseEntity patchTodoApp(@PathVariable("todoapp-id")
                                           @Positive long todoappId, @Valid @RequestBody TodoAppPatchDto todoAppPatchDto) {

        todoAppPatchDto.setTodoAppId(todoappId);

        TodoApp todoApp = toDoAppService.updateTodoApp(todoAppMapper.todoappPatchDtoToTodoapp(todoAppPatchDto));

        return new ResponseEntity<>(new SingleResponseDto<>(todoAppMapper.todoappToTodoappResponseDto(todoApp)), HttpStatus.OK);
    }

    @GetMapping("/{todoapp-id}")
    public ResponseEntity readTodoApp(@PathVariable("todoapp-id") @Positive long todoappId) {
        TodoApp todoApp = toDoAppService.readTodoApp(todoappId);

        return new ResponseEntity<>(new SingleResponseDto<>(todoAppMapper.todoappToTodoappResponseDto(todoApp)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity readTodoApps(@Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<TodoApp> pageTodoApps = toDoAppService.readTodoApps(page -1, size);
        List<TodoApp> todoApps = pageTodoApps.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(todoAppMapper.todoappsToTodoappResponseDtos(todoApps), pageTodoApps), HttpStatus.OK);

    }
    @DeleteMapping("/{todoapp-id}")
    public ResponseEntity deleteTodoApp(@PathVariable("todoapp-id") @Positive long todoappId) {
        toDoAppService.deleteTodoApp(todoappId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}