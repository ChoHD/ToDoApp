package com.codestates.ToDoApp.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class TodoAppPatchDto {

    private long todoappId;

    private String title;

    private int todoorder;

    private boolean completed;

    public void setTodoAppId(long todoappId) {
        this.todoappId = todoappId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
