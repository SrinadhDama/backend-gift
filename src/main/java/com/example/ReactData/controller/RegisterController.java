package com.example.ReactData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReactData.model.Register;
import com.example.ReactData.repo.RegisterRepo;
import com.example.ReactData.service.RegisterService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class RegisterController {
	
	@Autowired
	RegisterService registerService;
	
	@Autowired
	RegisterRepo registerRepo;
	
	@PostMapping("/register")
	public ResponseEntity<String>  registerData(@RequestBody Register register) {
		return registerService.registerDetails(register);	
	}
	
	@GetMapping("/{username}")
	public Register finduser(@PathVariable String username) {
		return registerRepo.findByUsername(username);
	}

}