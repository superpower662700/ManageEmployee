package com.example.springrestapi.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSaveRequest {
    @Size( min = 5 , message = "User Name must be least 5 characters")
    private String username;
    private String password;
    private String name;
    private String address;
}
