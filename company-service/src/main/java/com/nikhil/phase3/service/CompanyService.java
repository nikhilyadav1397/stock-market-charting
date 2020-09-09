package com.nikhil.phase3.service;

import java.util.List;

import com.nikhil.phase3.dto.CompanyDto;

public interface CompanyService {

	public CompanyDto createCompany(CompanyDto companyDto);

	public List<CompanyDto> getAllCompanies();

	public CompanyDto updateCompany(CompanyDto companyDto, String companyId);

	public void deleteCompany(String companyName);

	public List<CompanyDto> searchByPattern(String pattern);

	public List<CompanyDto> getCompanyBySector(String sectorName);

	public boolean ifExistsById(String id);

	public CompanyDto getCompanyByName(String name);

	public boolean companyNameExists(String companyName);

	public List<CompanyDto> getCompanyByStockExchange(String stockExchange);

	public CompanyDto getCompany(String companyId);
}