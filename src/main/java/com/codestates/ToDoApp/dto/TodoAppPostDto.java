package com.codestates.ToDoApp.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class TodoAppPostDto {

    private String title;

    private int todoorder;

    private boolean completed;
}
