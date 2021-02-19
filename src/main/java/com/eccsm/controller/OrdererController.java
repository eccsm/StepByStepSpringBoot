package com.eccsm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eccsm.model.Orderer;
import com.eccsm.model.vm.OrdererVM;
import com.eccsm.service.OrdererService;
import com.eccsm.utils.GenericResponse;

import javassist.NotFoundException;

@Controller
@SessionAttributes("username")
public class OrdererController {

	@Autowired
	OrdererService ordererService;

	@RequestMapping("/register")
	public String register(ModelMap model) {
		model.addAttribute("orderer", new Orderer());
		return "register";
	}

	@PostMapping("/register")
	public String createUser(@Valid Orderer orderer, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {

			return "register";
		}

		ordererService.save(orderer);
		model.put("registrationMessage", "Succesfully Created User " + orderer.getUsername());
		return "login";
	}

	@GetMapping("/orderers")
	List<OrdererVM> getUsers() {
		List<Orderer> ordererList = ordererService.getOrderers();
		List<OrdererVM> listVM = new ArrayList<OrdererVM>();

		if (ordererList.size() > 0) {
			for (int i = 0; i < ordererList.size(); i++) {
				listVM.add(new OrdererVM(ordererList.get(i)));

			}
		}
		return listVM;
	}

	@GetMapping("/current")
	public void getCurrent() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
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
