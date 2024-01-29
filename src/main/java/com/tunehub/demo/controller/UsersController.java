package com.tunehub.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.demo.entities.Users;
import com.tunehub.demo.services.UsersServices;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsersController {
	@Autowired
	UsersServices service;

	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		boolean user_status = service.emailExists(user.getEmail());
		if (user_status == false) {
			service.addUsers(user);
			System.out.println("user registered successfully");
		} else {
			System.out.println("user already registered");
		}
		return "home";
	}
	
	@PostMapping("/validate")
	public String validateUser(@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session) {
		if(service.validateUser(email,password)==true) {
			String role=service.getRole(email);
			
			session.setAttribute("email", email);
			if(role.equalsIgnoreCase("admin")) {
				return "adminHome";
			}
			else {
				return "customerHome";
			}
		}
		else {
			return "login";
		}
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	
}
