package com.nikhil.phase3.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.phase3.dto.CompanyDto;
import com.nikhil.phase3.dto.StockDto;
import com.nikhil.phase3.service.CompanyService;
import com.nikhil.phase3.service.StockService;

@RestController
@RequestMapping("companies")
public class CompanyController {

	private CompanyService companyService;
	private StockService stockService;

	public CompanyController(CompanyService companyService, StockService stockService) {
		this.companyService = companyService;
		this.stockService = stockService;
	}

	@GetMapping("/getAll")
	@Transactional
	public ResponseEntity<List<CompanyDto>> getAllCompanies() {
		return new ResponseEntity<List<CompanyDto>>(companyService.getAllCompanies(), HttpStatus.OK);
	}

	@GetMapping("/get/{companyId}")
	@Transactional
	public ResponseEntity<CompanyDto> getCompany(@PathVariable String companyId) {
		return new ResponseEntity<CompanyDto>(companyService.getCompany(companyId), HttpStatus.OK);
	}

	@PostMapping("/create")
	@Transactional
	public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
		if (companyService.companyNameExists(companyDto.getCompanyName())) {
			CompanyDto failResult = new CompanyDto();
			failResult.setCompanyName("Company name is taken.");
			return new ResponseEntity<CompanyDto>(failResult, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CompanyDto>(companyService.createCompany(companyDto), HttpStatus.OK);
	}

	@PostMapping("/stocks/create/{companyName}")
	@Transactional
	public ResponseEntity<StockDto> addStock(@RequestBody StockDto stockDto,
			@PathVariable("companyName") String companyName) {
		CompanyDto companyDto = companyService.getCompanyByName(companyName);
		stockService.addStock(stockDto);
		List<String> stockExchanges = companyDto.getStockExchanges();
		List<String> companyCodes = companyDto.getCompanyCodes();
		if (!stockExchanges.contains(stockDto.getStockExchange())) {
			companyCodes.add(stockDto.getCompanyCode());
			stockExchanges.add(stockDto.getStockExchange());
			companyService.updateCompany(companyDto, companyDto.getCompanyId());
		}
		return new ResponseEntity<StockDto>(stockDto, HttpStatus.OK);
	}

	@PutMapping("/update/{companyName}")
	@Transactional
	public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto companyDto,
			@PathVariable String companyName) {
		return new ResponseEntity<CompanyDto>(companyService.updateCompany(companyDto, companyName), HttpStatus.OK);

	}

	@DeleteMapping("/delete/{companyName}")
	@Transactional
	public ResponseEntity<String> deleteCompany(@PathVariable String companyName) {
		companyService.deleteCompany(companyName);
		return new ResponseEntity<String>("Successfully Deleted!!!", HttpStatus.OK);
	}

	@GetMapping("/findByPattern/{pattern}")
	public ResponseEntity<List<CompanyDto>> searchCompany(@PathVariable("pattern") String pattern) {
		return new ResponseEntity<List<CompanyDto>>(companyService.searchByPattern(pattern), HttpStatus.OK);
	}

	@GetMapping("/findByName/{companyName}")
	public ResponseEntity<List<Object>> getCompanyByName(@PathVariable("companyName") String companyName) {
		List<Object> result = new ArrayList<>();
		if (companyService.companyNameExists(companyName)) {
			CompanyDto companyDetails = companyService.getCompanyByName(companyName);
			result.add(companyDetails);
			List<String> companyCodes = companyDetails.getCompanyCodes();
			for (int i = 0; i < companyCodes.size(); i++) {
				StockDto latestStock = getCompanyStockPrice(companyCodes.get(i)).getBody();
				result.add(latestStock);
			}
			return new ResponseEntity<List<Object>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/stocks/{companyCode}")
	public ResponseEntity<StockDto> getCompanyStockPrice(@PathVariable("companyCode") String companyCode) {
		List<StockDto> stocks = stockService.getStockByCompanyCode(companyCode);
		if (stocks.size() > 0) {
			return new ResponseEntity<StockDto>(stocks.get(stocks.size() - 1), HttpStatus.OK);
		}
		return new ResponseEntity<StockDto>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/stocks/{companyCode}/{exchange}/{fromDateTime}/{toDateTime}")
	public ResponseEntity<Object> getCompanyStockPrice(@PathVariable String companyCode, @PathVariable String exchange,
			@PathVariable String fromDateTime, @PathVariable String toDateTime) {
		LocalDateTime from = LocalDateTime.parse(fromDateTime);
		LocalDateTime to = LocalDateTime.parse(toDateTime);
		List<StockDto> result = stockService.getStockByCompanyCodeTimePeriod(companyCode, exchange, from, to);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@GetMapping("/sector/{sectorName}")
	public ResponseEntity<List<CompanyDto>> getCompaniesBySector(@PathVariable String sectorName) {
		return new ResponseEntity<List<CompanyDto>>(companyService.getCompanyBySector(sectorName), HttpStatus.OK);
	}

	@GetMapping("/getCompaniesInExchange/{stockExchange}")
	public ResponseEntity<List<CompanyDto>> getCompaniesInStockExchange(
			@PathVariable("stockExchange") String stockExchange) {
		return ResponseEntity.ok().body(companyService.getCompanyByStockExchange(stockExchange));
	}
}
