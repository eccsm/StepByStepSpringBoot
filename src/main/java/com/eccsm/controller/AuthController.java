package com.eccsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eccsm.service.AuthService;

@Controller
public class AuthController {

	@Autowired
	AuthService authService;

	@RequestMapping("/")
	public String index(ModelMap model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		model.put("user", currentPrincipalName);

		return "welcome";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(ModelMap model) {
		model.put("errorMessage", "Invalid Credentials");
		return "login";
	}

}
