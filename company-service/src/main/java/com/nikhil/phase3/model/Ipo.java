package com.nikhil.phase3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ipo {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private String ipoId;
	private String companyName;
	private String stockExchange;
	private Float pricePerShare;
	private Integer noOfShares;
	private String openingDate;
	private String openingTime;
	private String remarks;
}
