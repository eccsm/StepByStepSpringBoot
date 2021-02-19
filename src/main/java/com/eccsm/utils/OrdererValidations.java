package com.eccsm.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.eccsm.model.Orderer;

@Component
public class OrdererValidations implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Orderer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Orderer orderer = (Orderer) target;
		if (orderer.getUsername().equalsIgnoreCase("noname")) {
			errors.rejectValue("username", null, "Username can not be NONAME");
		}
	}

}
