package com.nikhil.phase3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockExchangeDto {
	private String exchangeId;
	private String stockExchange;
	private String brief;
	private String contactAddress;
	private String remarks;

	public StockExchangeDto(String stockExchange, String brief, String contactAddress, String remarks) {
		this.stockExchange = stockExchange;
		this.brief = brief;
		this.contactAddress = contactAddress;
		this.remarks = remarks;
	}
}
