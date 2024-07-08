package com.example.springrestapi.dto.request;

import com.example.springrestapi.dto.response.EmployeeResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Null not display
public class ApiResponse <T> {
     int code = 1000;
     String message;
     T result;
}
