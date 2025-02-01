package com.example.Medinexus.Payload.Request;

import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email(message = "Invalid email format")
    private String email;
    
    private Set<String> roles;
    
    @NotBlank
    @Size(min = 8, max = 40)
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,40}$",
        message = "Password must have at least one uppercase letter, one lowercase letter, one number, and one special character."
    )
    private String password;

}
