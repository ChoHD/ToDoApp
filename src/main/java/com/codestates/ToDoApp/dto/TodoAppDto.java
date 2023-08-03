package com.codestates.ToDoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
    public static class Patch {

        private long todoappId;

        private String title;

        private int todoorder;

        private boolean completed;

        public void setTodoAppId(long todoAppId) {
            this.todoappId = todoAppId;
        }
    }
    @Builder
    @Getter
    public static class Response {
        private long todoappId;
        private String title;
        private int todoorder;
        private boolean completed;
    }
}
