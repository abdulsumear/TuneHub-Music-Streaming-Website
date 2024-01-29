package com.tunehub.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.demo.entities.PlayList;
import com.tunehub.demo.repositories.PlayListRepository;

@Service
public class PlayListServiceImplementation implements PlayListService {
     
	@Autowired
	PlayListRepository repo;
	
	@Override
	public void addPlayList(PlayList playList) {
		repo.save(playList);
		
	}

	@Override
	public List<PlayList> fetchAllPlayList() {
		
		return repo.findAll();
	}

}
