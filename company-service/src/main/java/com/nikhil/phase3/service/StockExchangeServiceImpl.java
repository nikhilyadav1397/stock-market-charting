package com.nikhil.phase3.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.nikhil.phase3.dto.StockExchangeDto;
import com.nikhil.phase3.model.StockExchange;
import com.nikhil.phase3.repository.CompanyRepository;
import com.nikhil.phase3.repository.StockExchangeRepository;

@Service
public class StockExchangeServiceImpl implements StockExchangeService {

	private StockExchangeRepository stockExchangeRepo;
	private CompanyRepository companyRepo;
	private ModelMapper mapper;

	public StockExchangeServiceImpl(StockExchangeRepository stockExchangeRepository, CompanyRepository companyRepo,
			ModelMapper modelMapper) {
		this.stockExchangeRepo = stockExchangeRepository;
		this.mapper = modelMapper;
		this.companyRepo = companyRepo;
	}

	@Override
	public List<StockExchangeDto> getStockExchangesList() {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<StockExchangeDto>>() {
		}.getType();
		List<StockExchangeDto> stockExchangeDtos = mapper.map(stockExchangeRepo.findAll(), listType);
		return stockExchangeDtos;
	}

	@Override
	public StockExchangeDto createStockExchange(StockExchangeDto stockExchangeDto) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		stockExchangeDto.setExchangeId(UUID.randomUUID().toString());
		StockExchange exchange = stockExchangeRepo.save(mapper.map(stockExchangeDto, StockExchange.class));
		return mapper.map(exchange, StockExchangeDto.class);
	}

	@Override
	public StockExchangeDto deleteStockExchange(String id) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		StockExchangeDto stockExchangeDto = mapper.map(stockExchangeRepo.findByExchangeId(id), StockExchangeDto.class);
		stockExchangeRepo.deleteByExchangeId(id);
		return stockExchangeDto;
	}

	@Override
	public boolean existsById(String id) {
		return stockExchangeRepo.existsByExchangeId(id);
	}

	@Override
	public boolean existsByStockExchange(String stockExchange) {
		return stockExchangeRepo.existsByStockExchange(stockExchange);
	}

	@Override
	public List<String> getAllCompanyExchanges(String companyId) {
		Integer id = companyRepo.findByCompanyId(companyId).get().getId();
		List<String> exchangesString = stockExchangeRepo
				.findAllByCompanyId(id);
		return exchangesString;
	}
}
