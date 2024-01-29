package com.tunehub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.demo.entities.Song;
import com.tunehub.demo.services.SongService;


@Controller
public class SongController {
	@Autowired
	SongService service;
	
	@PostMapping("/addsong")
	public String addSong(@ModelAttribute Song song) {
		if(service.songExists(song.getName())==false) {
			service.addSong(song);
		}
		
		return "adminHome";
	}
	
	@GetMapping("/viewsongs")
	public String viewSongs(Model model) {
		List<Song> songs = service.fetchAllSongs();
		model.addAttribute("songs", songs);
		
		return "displaysongs";
	}
	
	
	@GetMapping("/playsongs")
	public String playSongs(Model model) {
		boolean payment_status=false;
		List<Song> songs = service.fetchAllSongs();
		model.addAttribute("songs", songs);
		if(payment_status==true) {
			return "displaysongs";
		}
		else 
		{
		return "makepayment";
		}
	}
	
}
