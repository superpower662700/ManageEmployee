package com.example.springrestapi.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter
@Slf4j
public class logger implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        String uri = httpServletRequest.getRequestURI();
//        String requestBody = StreamUtils.copyToString(servletRequest.getInputStream(), StandardCharsets.UTF_8); // Lấy dữ liệu body
        log.info("Request: " + method + " " + uri); // Ghi nhật ký thông tin chi tiết
        String idParam = httpServletRequest.getParameter("id");
        if (idParam != null) {
            try {
                Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                // Trả về phản hồi lỗi
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_BAD_REQUEST, "Tham số id không hợp lệ");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
