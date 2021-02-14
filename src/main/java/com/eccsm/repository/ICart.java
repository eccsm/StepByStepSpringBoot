package com.eccsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eccsm.model.Cart;
import com.eccsm.model.Orderer;

public interface ICart extends JpaRepository<Cart,Long> {
	
	Cart findByOrderer(Orderer orderer);

}
