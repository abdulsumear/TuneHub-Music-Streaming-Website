package com.tunehub.demo.services;

import java.util.List;

import com.tunehub.demo.entities.PlayList;

public interface PlayListService {

	public void addPlayList(PlayList playList);

	public List<PlayList> fetchAllPlayList();

}
