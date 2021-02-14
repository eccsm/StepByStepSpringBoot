package com.eccsm.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eccsm.model.Orderer;
import com.eccsm.model.vm.OrdererVM;
import com.eccsm.repository.IOrderer;

import javassist.NotFoundException;

@Service
public class OrdererService {

	@Autowired
	IOrderer ordererRepository;

	PasswordEncoder passwordEncoder;

	public OrdererService(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	public List<Orderer> getOrderers() {

		return ordererRepository.findAll();
	}
	
	public Orderer getCurrentOrderer(Orderer orderer) {

		return ordererRepository.findByUsernameNot(orderer.getUsername());
	}

	public Orderer getByUsername(String username) throws NotFoundException {
		Orderer inDB = ordererRepository.findByUsername(username);
		if (inDB == null) {
			throw new NotFoundException("not found");
		}
		return inDB;
	}

	public void save(@Valid Orderer orderer) {
		orderer.setPassword(this.passwordEncoder.encode(orderer.getPassword()));
		ordererRepository.save(orderer);

	}

	public Orderer updateOrderer(String username, @Valid OrdererVM updatedOrderer) throws NotFoundException {
		Orderer inDB = getByUsername(username);
		inDB.setEmail(updatedOrderer.getEmail());

		return ordererRepository.save(inDB);
	}

	public void deleteOrderer(String username) {
		Orderer inDB = ordererRepository.findByUsername(username);
		ordererRepository.delete(inDB);

	}

}
