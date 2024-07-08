package com.example.springrestapi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // giúp gắn dũ liệu dễ dàng
@FieldDefaults(level = AccessLevel.PRIVATE) // mặc định các biến được tạo là private
public class AuthenticationResponse {
    String token;
}
