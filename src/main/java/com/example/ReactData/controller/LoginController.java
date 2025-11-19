package com.example.ReactData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReactData.model.Login;
import com.example.ReactData.service.LoginService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
		return loginService.verifyLogin(login);
	}
}
