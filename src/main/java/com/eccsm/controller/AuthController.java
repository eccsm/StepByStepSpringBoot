package com.eccsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eccsm.service.AuthService;
import com.eccsm.utils.AuthResponse;
import com.eccsm.utils.Credentials;

@RestController
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/auth")
	AuthResponse handleAuthentication(@RequestBody Credentials credentials) throws Exception {
		return authService.authorization(credentials);
	}
	
	@PostMapping("/signout")
	String handleLogout(@RequestHeader(name = "Authorization") String authorization) {
		String token = authorization.substring(7);
		authService.clearToken(token);
		return "Logout success";
	}

}
