package com.example.springrestapi.mapper;

import com.example.springrestapi.dto.request.EmployeeSaveRequest;
import com.example.springrestapi.dto.request.EmployeeUpdateRequest;
import com.example.springrestapi.dto.response.EmployeeResponse;
import com.example.springrestapi.entity.Employee;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//  ánh xa Dto sang Entity
@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeSaveRequest request);

    EmployeeResponse toEmployeeResponse(Employee employee);
    void updateEmployee(@MappingTarget Employee employee, EmployeeUpdateRequest request);
}
