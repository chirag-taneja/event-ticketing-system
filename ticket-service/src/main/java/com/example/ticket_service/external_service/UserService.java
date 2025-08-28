package com.example.ticket_service.external_service;

import com.example.ticket_service.external_class.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserService {

    @GetMapping("/api/users")
    ResponseEntity<User> getUserById(@RequestParam Long id);
}
