package com.example.springrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    USERNAME_INVALID(1002, "User Name must be least 5 characters"),
    LOGIN_USER_FAILED(1003, "Login User Failed"),
    LOGIN_PASSWORD_FAILED(1004, "Login Password Failed"),
    ;
    private int code;
    private String message;

}
