package com.nikhil.phase3.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String stockId;
	private String companyCode;
	private String stockExchange;
	private float pricePerShare;
	private LocalDateTime dateTime;

	public Stock(String companyCode, String stockExchange, float pricePerShare, LocalDateTime dateTime) {
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.pricePerShare = pricePerShare;
		this.dateTime = dateTime;
	}
}
