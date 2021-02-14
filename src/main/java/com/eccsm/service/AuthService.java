package com.eccsm.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eccsm.model.Orderer;
import com.eccsm.model.Token;
import com.eccsm.model.vm.OrdererVM;
import com.eccsm.repository.IOrderer;
import com.eccsm.repository.IToken;
import com.eccsm.utils.AuthResponse;
import com.eccsm.utils.Credentials;

@Service
public class AuthService {
	
	@Autowired
	IOrderer ordererRepository;
	@Autowired
	IToken tokenRepository;
	
	PasswordEncoder passwordEncoder;
	
	
	public AuthService(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}


	public AuthResponse authorization(Credentials credentials) throws Exception
	{
		Orderer inDB = ordererRepository.findByUsername(credentials.getUsername());
		if(inDB == null) {
			throw new Exception();			
		}
		boolean matches = passwordEncoder.matches(credentials.getPassword(), inDB.getPassword());
		if(!matches) {
			throw new Exception();
		}
		OrdererVM ordererVm = new OrdererVM(inDB);
		String token = generateRandomToken();
		
		Token tokenEntity = new Token();
		tokenEntity.setToken(token);
		tokenEntity.setOrderer(inDB);
		tokenRepository.save(tokenEntity);
		AuthResponse response = new AuthResponse();
		response.setOrdererVm(ordererVm);
		response.setToken(token);
		return response;
	}
	
	@Transactional
	public UserDetails getUserDetails(String token) {
		Optional<Token> optionalToken = tokenRepository.findById(token);
		if(!optionalToken.isPresent()) {
			return null;
		}
		return optionalToken.get().getOrderer();
	}
	
	
	public String generateRandomToken() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}


	public void clearToken(String token) {
		tokenRepository.deleteById(token);
	}
	
	

}
