package com.nikhil.phase3.service;

import java.time.LocalDateTime;
import java.util.List;

import com.nikhil.phase3.dto.StockDto;

public interface StockService {
	public StockDto addStock(StockDto stockDto);

	public List<StockDto> findAll();

	public List<StockDto> getStockByCompanyCode(String stockCode);

	public List<StockDto> getStockByCompanyCodeTimePeriod(String companyCode, String exchange,
			LocalDateTime from, LocalDateTime to);

	public boolean ifExistsById(String id);
}
