package com.codestates.ToDoApp.exception;

import lombok.Getter;

public enum ExceptionCode {
    TITLE_NOT_FOUND(404, "Title not found"),
    TITLE_EXISTS(409, "Title exists"),
    TODOORDER_EXISTS(409, "TodoOrder exists");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
