package com.Blog2.Payload;

import lombok.Data;
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}