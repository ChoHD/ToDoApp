package com.codestates.ToDoApp.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TodoAppResponseDto {
    private long Id;
    private String title;
    private int todoorder;
    private boolean completed;
}
