package com.Blog2.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainClass {
    public static void main(String[] args) {
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.print(encoder.encode("test"));
    }
}
