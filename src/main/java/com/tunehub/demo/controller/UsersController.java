package com.tunehub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.demo.entities.Song;
import com.tunehub.demo.entities.Users;
import com.tunehub.demo.services.SongService;
import com.tunehub.demo.services.UsersServices;

import jakarta.servlet.http.HttpSession;


@Controller
public class UsersController {
	@Autowired
	UsersServices service;
	@Autowired
	SongService songService;

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
			HttpSession session,Model model) {
		if(service.validateUser(email,password)==true) {
			String role=service.getRole(email);
			
			session.setAttribute("email", email);
			if(role.equalsIgnoreCase("admin")) {
				return "adminHome";
			}
			else {
				Users user = service.getUser(email);
				boolean userStatus=user.isPremium();
				List<Song> songs = songService.fetchAllSongs();
				model.addAttribute("songs", songs);
				model.addAttribute("ispremium",userStatus);
				
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
