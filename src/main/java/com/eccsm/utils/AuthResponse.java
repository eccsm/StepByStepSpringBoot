package com.eccsm.utils;

import com.eccsm.model.vm.OrdererVM;

import lombok.Data;

@Data
public class AuthResponse {
	
	private OrdererVM ordererVm;
	
	private String token;

}
