package com.hcltech.retail_ordering.dto;

import lombok.*;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}
