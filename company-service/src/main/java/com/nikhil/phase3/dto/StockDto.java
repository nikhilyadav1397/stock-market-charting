package com.nikhil.phase3.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
	private String stockId;
	private String companyCode;
	private String stockExchange;
	private float pricePerShare;
	private LocalDateTime dateTime;

	public StockDto(String companyCode, String stockExchange, float pricePerShare, LocalDateTime dateTime) {
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.pricePerShare = pricePerShare;
		this.dateTime = dateTime;
	}
}
