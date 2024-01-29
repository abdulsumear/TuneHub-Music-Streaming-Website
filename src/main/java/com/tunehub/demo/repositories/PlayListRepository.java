package com.tunehub.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.demo.entities.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Integer> {

}
