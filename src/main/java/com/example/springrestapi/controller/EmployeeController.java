package com.example.springrestapi.controller;

import com.example.springrestapi.dto.request.ApiResponse;
import com.example.springrestapi.dto.request.EmployeeSaveRequest;
import com.example.springrestapi.dto.request.EmployeeUpdateRequest;
import com.example.springrestapi.dto.response.EmployeeResponse;
import com.example.springrestapi.entity.Employee;
import com.example.springrestapi.service.EmployeeService;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class    EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping
    public ApiResponse search(){
        List<EmployeeResponse> e = employeeService.findAll();
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(0);
        apiResponse.setMessage("Success");
        apiResponse.setResult(e);
        return apiResponse;
    }
    @GetMapping("/{id}")
    public ApiResponse fillByIdEmployee(@PathVariable("id") int id) {
        EmployeeResponse employee = employeeService.findById(id);
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(0);
        apiResponse.setMessage("Success");
        apiResponse.setResult(employee);
        return apiResponse;
    }
    @PutMapping
    public ApiResponse update(
            @RequestBody EmployeeUpdateRequest request
            ){
        EmployeeResponse e = employeeService.update(request);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(0);
        apiResponse.setMessage("Success");
        apiResponse.setResult(e);
        return apiResponse;
    }

    @PostMapping
    public ApiResponse save(
            @RequestBody @Valid EmployeeSaveRequest request
    ){
        EmployeeResponse e = employeeService.save(request);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(0);
        apiResponse.setMessage("Success");
        apiResponse.setResult(e);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable int id){
        ApiResponse apiResponse = new ApiResponse();
        if(employeeService.deleteById(id)){
            apiResponse.setCode(0);
            apiResponse.setMessage("Success");
            return apiResponse;
        }
        apiResponse.setCode(-1);
        apiResponse.setMessage("Delete failed");
        return apiResponse;
    }
}
