package com.codestates.ToDoApp.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class TodoAppPatchDto {

    private long todoAppId;

    @NotBlank(message = "할일은 비어있으면 안됩니다.")
    private String title;

    @NotBlank(message = "값이 비어 있으면 안됩니다.")
    private int todoorder;

    @NotBlank(message = "ture 또는 false 값만 들어가야 합니다")
    @Pattern(regexp = "^(true|false)$")
    private boolean completed;

    public void setTodoAppId(long todoAppId) {
        this.todoAppId = todoAppId;
    }
}
