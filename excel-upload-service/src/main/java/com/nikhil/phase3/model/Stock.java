package com.nikhil.phase3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	private String stockId;
	private String stockExchange;
	private Integer companyCode;
	private double price;
	private String time;
	private String date; 
}
