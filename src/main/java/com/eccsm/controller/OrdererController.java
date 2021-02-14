package com.eccsm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eccsm.model.Orderer;
import com.eccsm.model.vm.OrdererVM;
import com.eccsm.service.OrdererService;
import com.eccsm.utils.CurrentUser;
import com.eccsm.utils.GenericResponse;

import javassist.NotFoundException;

@RestController
public class OrdererController {

	@Autowired
	OrdererService ordererService;

	@PostMapping("/orderers")
	public GenericResponse createUser(@Valid @RequestBody Orderer orderer) {
		ordererService.save(orderer);
		return new GenericResponse("user created");
	}

	@GetMapping("/orderers")
	OrdererVM getUsers() {
		return (OrdererVM) ordererService.getOrderers();
	}
	
	@GetMapping("/current")
	Orderer getCurrent(@CurrentUser Orderer orderer) {
		return ordererService.getCurrentOrderer(orderer);
	}

	@GetMapping("/orderers/{username}")
	OrdererVM getUser(@PathVariable String username) throws NotFoundException {
		Orderer user = ordererService.getByUsername(username);
		return new OrdererVM(user);
	}

	@PutMapping("/orderers/{username}")
	@PreAuthorize("#username == principal.username")
	OrdererVM updateUser(@Valid @RequestBody OrdererVM updatedOrderer, @PathVariable String username)
			throws NotFoundException {
		Orderer user = ordererService.updateOrderer(username, updatedOrderer);
		return new OrdererVM(user);
	}

	@DeleteMapping("/orderers/{username}")
	@PreAuthorize("#username == principal.username")
	GenericResponse deleteUser(@PathVariable String username) {
		ordererService.deleteOrderer(username);
		return new GenericResponse("User is removed");
	}

}
