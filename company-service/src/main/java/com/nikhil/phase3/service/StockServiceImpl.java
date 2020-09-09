package com.nikhil.phase3.service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.nikhil.phase3.dto.StockDto;
import com.nikhil.phase3.model.Stock;
import com.nikhil.phase3.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	private StockRepository stockRepository;
	private ModelMapper modelMapper;

	public StockServiceImpl(StockRepository stockRepository, ModelMapper modelMapper) {
		this.stockRepository = stockRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public StockDto addStock(StockDto stockDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		stockDto.setStockId(UUID.randomUUID().toString());
		Stock stock = stockRepository.save(modelMapper.map(stockDto, Stock.class));
		return modelMapper.map(stock, StockDto.class);
	}

	@Override
	public List<StockDto> findAll() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<StockDto>>() {
		}.getType();
		List<StockDto> stockDtos = modelMapper.map(stockRepository.findAll(), listType);
		return stockDtos;
	}

	@Override
	public List<StockDto> getStockByCompanyCode(String companyCode) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<StockDto>>() {
		}.getType();
		List<StockDto> stockDtos = modelMapper.map(stockRepository.findStocksForCompany(companyCode), listType);
		return stockDtos;
	}

	@Override
	public List<StockDto> getStockByCompanyCodeTimePeriod(String companyCode, String exchange, LocalDateTime from,
			LocalDateTime to) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<StockDto>>() {
		}.getType();
		List<StockDto> stockDtos = modelMapper
				.map(stockRepository.findStocksFromTo(companyCode, exchange, from, to), listType);
		return stockDtos;
	}

	@Override
	public boolean ifExistsById(String id) {
		return stockRepository.existsById(id);
	}
}
