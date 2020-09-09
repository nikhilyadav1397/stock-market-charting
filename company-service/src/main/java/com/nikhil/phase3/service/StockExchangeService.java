package com.nikhil.phase3.service;

import java.util.List;

import com.nikhil.phase3.dto.StockExchangeDto;

public interface StockExchangeService {
	public List<StockExchangeDto> getStockExchangesList();

	public StockExchangeDto createStockExchange(StockExchangeDto stockExchangeDto);

	public StockExchangeDto deleteStockExchange(String id);

	public boolean existsById(String id);

	public boolean existsByStockExchange(String stockExchange);

	public List<String> getAllCompanyExchanges(String companyId);
}
