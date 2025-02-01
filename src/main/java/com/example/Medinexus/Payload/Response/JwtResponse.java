package com.example.Medinexus.Payload.Response;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtResponse {
    
    private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String email;
	private List<String> roles;
}
