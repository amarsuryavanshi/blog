package com.Blog2.Payload;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PostDto {
    private  Long id;
    @NotEmpty
    @Size(min = 4, message = " name should have at least 4 characters")
    private String name;
    @NotEmpty(message = "Email address is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits")
    private String mobile;

}
