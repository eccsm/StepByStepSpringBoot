package com.eccsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eccsm.model.Orderer;

public interface IOrderer extends JpaRepository<Orderer,Long> {
	
	Orderer findByUsername(String username);
	
	Orderer findByUsernameNot(String username);



}
