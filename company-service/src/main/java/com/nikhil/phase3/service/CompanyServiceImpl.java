package com.nikhil.phase3.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.nikhil.phase3.dto.CompanyDto;
import com.nikhil.phase3.model.Company;
import com.nikhil.phase3.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepo;
	private ModelMapper mapper;

	public CompanyServiceImpl(CompanyRepository companyRepo, ModelMapper mapper) {
		this.companyRepo = companyRepo;
		this.mapper = mapper;
	}

	@Override
	public List<CompanyDto> getAllCompanies() {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<CompanyDto>>() {
		}.getType();
		List<CompanyDto> companyDtos = mapper.map(companyRepo.findAll(), listType);
		return companyDtos;
	}

	@Override
	public CompanyDto createCompany(CompanyDto companyDto) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		companyDto.setCompanyId(UUID.randomUUID().toString());
		Company company = companyRepo.save(mapper.map(companyDto, Company.class));
		return mapper.map(company, CompanyDto.class);
	}

	@Override
	public void deleteCompany(String companyName) {
		Company company = companyRepo.findByCompanyName(companyName).get();
		companyRepo.delete(company);
	}

	@Override
	public CompanyDto updateCompany(CompanyDto companyDto, String companyName) {
		Company company = companyRepo.findByCompanyName(companyName).get();
		company.setCompanyName(companyDto.getCompanyName());
		company.setCeo(companyDto.getCeo());
		company.setBrief(companyDto.getBrief());
		company.setSector(companyDto.getSector());
		company.setStockExchanges(companyDto.getStockExchanges());
		company.setCompanyCodes(companyDto.getCompanyCodes());
		companyRepo.save(company);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(company, CompanyDto.class);
	}
	
	@Override
	public CompanyDto getCompanyByName(String companyName) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Company company = companyRepo.findByCompanyName(companyName).get();
		return mapper.map(company, CompanyDto.class);
	}

	@Override
	public List<CompanyDto> searchByPattern(String pattern) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<CompanyDto>>() {
		}.getType();
		List<CompanyDto> companyDtos = mapper.map(companyRepo.findByCompanyNameContaining(pattern), listType);
		return companyDtos;
	}

	@Override
	public List<CompanyDto> getCompanyBySector(String sectorName) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<CompanyDto>>() {
		}.getType();
		List<CompanyDto> companyDtos = mapper.map(companyRepo.findBySector(sectorName), listType);
		return companyDtos;
	}

	@Override
	public boolean ifExistsById(String id) {
		return companyRepo.existsById(id);
	}

	@Override
	public boolean companyNameExists(String companyName) {
		return companyRepo.existsByCompanyName(companyName);
	}

	@Override
	public List<CompanyDto> getCompanyByStockExchange(String stockExchange) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<CompanyDto>>() {
		}.getType();
		List<CompanyDto> companyDtos = mapper.map(companyRepo.findByStockExchange(stockExchange), listType);
		return companyDtos;
	}

	@Override
	public CompanyDto getCompany(String companyId) {
		Company company = companyRepo.findByCompanyId(companyId).get();
		return mapper.map(company, CompanyDto.class);
	}
}
