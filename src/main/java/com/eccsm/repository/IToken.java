package com.eccsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eccsm.model.Token;

public interface IToken extends JpaRepository<Token,String> {

}
