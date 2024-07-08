package com.example.springrestapi.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // giúp gắn dũ liệu dễ dàng
@FieldDefaults(level = AccessLevel.PRIVATE) // mặc định các biến được tạo là private
public class EmployeeResponse {
      int id;
     String username;
     String password;
     String name;
     String address;
}
