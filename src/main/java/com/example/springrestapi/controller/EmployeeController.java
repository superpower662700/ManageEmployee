package com.example.springrestapi.controller;

import com.example.springrestapi.dto.request.ApiResponse;
import com.example.springrestapi.dto.request.EmployeeSaveRequest;
import com.example.springrestapi.dto.request.EmployeeUpdateRequest;
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
    public ApiResponse<List<Employee>> search(){
        List<Employee> employees = employeeService.findAll();
        ApiResponse<List<Employee>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(0);
        apiResponse.setMessage("Success");
        apiResponse.setResult(employees);
        return apiResponse;
    }
    @GetMapping("/{id}")
    public ApiResponse fillByIdEmployee(@PathVariable("id") int id) {
        Employee employee = employeeService.findById(id);
        ApiResponse<Employee> apiResponse = new ApiResponse<>();
//        if (employee == null) {
//            apiResponse.setCode(-1);
//            apiResponse.setMessage("Employee not found");
//            return apiResponse;
//        }
        apiResponse.setCode(0);
        apiResponse.setMessage("Success");
        apiResponse.setResult(employee);
        return apiResponse;
    }
    @PutMapping
    public ApiResponse update(
            @RequestBody EmployeeUpdateRequest request
            ){
        Employee employee = new Employee();
        employee.setId(request.getId());
        employee.setName(request.getName());
        employee.setAddress(request.getAddress());
        employee.setPassword(request.getPassword());
        Employee e = employeeService.update(employee);
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
        Employee employee = new Employee();
        employee.setUsername(request.getUsername());
        employee.setName(request.getName());
        employee.setAddress(request.getAddress());
        employee.setPassword(request.getPassword());
        Employee e = employeeService.save(employee);

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
