package com.example.springrestapi.dto.request;

import com.example.springrestapi.exception.ErrorCode;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // giúp gắn dũ liệu dễ dàng
@FieldDefaults(level = AccessLevel.PRIVATE) // mặc định các biến được tạo là private
public class EmployeeSaveRequest {
    @Size( min = 5 , message = "USERNAME_INVALID")
     String username;
     String password;
     String name;
     String address;
}
