package com.eccsm.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Cart {

	public enum Status {
		ORDERED, READY, DELIVERED;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date onSell;

	@ManyToOne
	private Orderer orderer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date onDelivery;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@PrePersist
	protected void onCreate() {
		onSell = new Date();
	}

}
