package com.codestates.ToDoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TodoAppDto {
    @Getter
    @AllArgsConstructor
    public static class Post {

        private String title;

        private int todoorder;

        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private long todoAppId;

        @NotBlank(message = "할일은 비어있으면 안됩니다.")
        private String title;

        private int todoorder;

        private boolean completed;

        public void setTodoAppId(long todoAppId) {
            this.todoAppId = todoAppId;
        }
    }
    @AllArgsConstructor
    @Getter
    public static class Response {
        private long todoAppId;
        private String title;
        private int todoorder;
        private boolean completed;
    }
}
