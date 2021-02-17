package com.eccsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eccsm.service.AuthService;

@Controller
public class AuthController {

	@Autowired
	AuthService authService;


//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String showWelcomePage(ModelMap model, @RequestParam String username, @RequestParam String password) {
//
//		Credentials credentials = new Credentials();
//		credentials.setUsername(username);
//		credentials.setPassword(password);
//		boolean isValidUser = authService.isValidUser(credentials);
//
//		if (!isValidUser) {
//			model.put("errorMessage", "Invalid Credentials");
//			return "login";
//		}
//
//		model.put("username", username);
//		model.put("password", password);
//
//
//		return "welcome";
//	}
//
//	@PostMapping("/signout")
//	String handleLogout(@RequestHeader(name = "Authorization") String authorization) {
//		String token = authorization.substring(7);
//		authService.clearToken(token);
//		return "Logout success";
//	}

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "You made it!";
	}

	// Login form
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// Login form with error
	@RequestMapping("/login-error")
	public String loginError(ModelMap model) {
		model.put("errorMessage", "Invalid Credentials");
		return "login";
	}

}
