package com.codestates.ToDoApp.mapper;

import com.codestates.ToDoApp.dto.TodoAppDto;
import com.codestates.ToDoApp.dto.TodoAppPatchDto;
import com.codestates.ToDoApp.dto.TodoAppPostDto;
import com.codestates.ToDoApp.dto.TodoAppResponseDto;
import com.codestates.ToDoApp.entity.TodoApp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import javax.validation.Valid;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoAppMapper {
    TodoApp todoappPostDtoToTodoapp(TodoAppPostDto todoAppPostDto);
    TodoApp todoappPatchDtoToTodoapp(TodoAppPatchDto todoAppPatchDto);
    TodoAppResponseDto todoappToTodoappResponseDto(TodoApp toDoApp);
    List<TodoAppResponseDto> todoappsToTodoappResponseDtos(List<TodoApp> toDoApps);
}
