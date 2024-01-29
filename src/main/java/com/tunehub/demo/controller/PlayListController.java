package com.tunehub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.demo.entities.PlayList;
import com.tunehub.demo.entities.Song;
import com.tunehub.demo.services.PlayListService;
import com.tunehub.demo.services.SongService;


@Controller
public class PlayListController {
	@Autowired
	SongService service;
	@Autowired
	PlayListService playListService;
	
	

	@GetMapping("/createplaylist")
	public String createplaylits(Model model) {
		List<Song> songsList= service.fetchAllSongs();
		model.addAttribute("song", songsList);
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute PlayList playList) {
		playListService.addPlayList(playList);
		List<Song> songs=playList.getSongs();
		System.out.println(songs);
		for(Song s :songs) {
			s.getPlaylist().add(playList);
			service.updateSong(s);
		}
		
		return "adminHome";
	}
	
	@GetMapping("/viewplaylists")
	public String viewplaylists(Model model) {
		List<PlayList> playlists=playListService.fetchAllPlayList();
		model.addAttribute("playlists",playlists);
		System.out.println(playlists);
		return "displayplaylists";
	}
	
	

}
