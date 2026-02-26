package com.hcltech.retail_ordering.controller;

import org.springframework.web.bind.annotation.*;

import com.hcltech.retail_ordering.dto.LoginRequest;
import com.hcltech.retail_ordering.entity.User;
import com.hcltech.retail_ordering.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    // @PostMapping("/login")
    // public String login(@RequestParam String username,
    //                     @RequestParam String password) {
    //     return authService.login(username, password);
    // }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword());
    }
}