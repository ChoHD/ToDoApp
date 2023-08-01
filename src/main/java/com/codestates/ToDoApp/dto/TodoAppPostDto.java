package com.codestates.ToDoApp.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class TodoAppPostDto {

    @NotBlank(message = "할일은 비어있으면 안됩니다.")
    private String title;

    private int todoorder;

    private boolean completed;
}
