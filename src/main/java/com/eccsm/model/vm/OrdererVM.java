package com.eccsm.model.vm;

import com.eccsm.model.Orderer;

import lombok.Data;

@Data
public class OrdererVM {
	
	private String username;
	
	private String email;

	public OrdererVM(Orderer orderer) {
		super();
		this.username = orderer.getUsername();
		this.email = orderer.getEmail();
	}
	
	

}
