package com.tunehub.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.demo.entities.Song;
import com.tunehub.demo.repositories.SongRepository;

@Service
public class SongServiceImplementation 
                             implements SongService{
	@Autowired
	SongRepository repo;

	@Override
	public void addSong(Song song) {
		repo.save(song);
		
	}

	@Override
	public List<Song> fetchAllSongs() {
		
		return repo.findAll();
	}

	@Override
	public boolean songExists(String name) {
		boolean song_status=false;
		Song song=repo.findByName(name);
		if(song!=null) {
			song_status=true;
		}
		return song_status;
	}

	@Override
	public void updateSong(Song song) {
		repo.save(song);
		
	}

}
