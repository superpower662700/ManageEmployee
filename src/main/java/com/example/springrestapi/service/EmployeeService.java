package com.example.springrestapi.service;

import com.example.springrestapi.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    public List<Employee> findAll();
    public Employee findById(int id);
    public Employee save(Employee employee);
    public Employee update(Employee employee);
    public boolean deleteById(int id);

}
