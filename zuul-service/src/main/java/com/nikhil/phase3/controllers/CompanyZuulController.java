package com.nikhil.phase3.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("companies")
@CrossOrigin(origins = { "http://localhost:4200"})
public class CompanyZuulController {

	public CompanyZuulController(CompanyClient companyClient) {
		this.companyClient = companyClient;
	}

	private CompanyClient companyClient;

	@GetMapping("/getAll")
	public ResponseEntity<List<Object>> getAllCompanies() {
		return companyClient.getAllCompanies();
	}

	@GetMapping("/get/{companyId}")
	public ResponseEntity<Object> getCompany(@PathVariable String companyId) {
		return companyClient.getCompany(companyId);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createCompany(@RequestBody Object companyRequestModel) {
		return companyClient.createCompany(companyRequestModel);
	}

	@PutMapping("/update/{companyName}")
	public ResponseEntity<Object> updateCompany(@RequestBody Object companyRequestModel,
			@PathVariable String companyName) {
		return companyClient.updateCompany(companyRequestModel, companyName);
	}

	@DeleteMapping("/delete/{companyName}")
	public ResponseEntity<String> deleteCompany(@PathVariable String companyName) {
		return companyClient.deleteCompany(companyName);
	}

	@PostMapping("/stocks/create/{companyName}")
	public ResponseEntity<Object> addStock(@RequestBody Object stockRequestModel, @PathVariable String companyName) {
		return companyClient.addStock(stockRequestModel, companyName);
	}

	@GetMapping("/stocks/{companyCode}")
	public ResponseEntity<Object> getStock(@PathVariable String companyCode) {
		return companyClient.getStock(companyCode);
	}

	@GetMapping("/findByName/{companyName}")
	public ResponseEntity<Object> getCompanyByName(@PathVariable String companyName) {
		return companyClient.getCompanyByName(companyName);
	}

	@GetMapping("/findByPattern/{pattern}")
	public ResponseEntity<List<Object>> searchCompany(@PathVariable String pattern) {
		return companyClient.searchCompany(pattern);
	}

	@GetMapping("/stocks/{companyCode}/{exchange}/{fromDateTime}/{toDateTime}")
	public ResponseEntity<Object> getCompanyStockPrice(@PathVariable String companyCode, @PathVariable String exchange,
			@PathVariable String fromDateTime, @PathVariable String toDateTime) {
		return companyClient.getCompanyStockPrice(companyCode, exchange, fromDateTime, toDateTime);
	}

	@PostMapping("/addExchange/{companyName}/{stockExchange}/{companyCode}")
	public ResponseEntity<Object> addExchange(@RequestBody Object exchange) {
		return companyClient.addExchange(exchange);
	}
	
	@GetMapping("/getAllCompanyExchanges/{companyId}")
	public ResponseEntity<List<Object>> getAllCompanyExchanges(@PathVariable String companyId) {
		return companyClient.getAllCompanyExchanges(companyId);
	}

//	@GetMapping("/getCompanyStockPrice/{companyId}/from/{from}/to/{to}")
//	public ResponseEntity<List<Object>> getCompanyStockPrice(@PathVariable Integer companyId, @PathVariable LocalDateTime from,
//			@PathVariable LocalDateTime to) {
//		return companyClient.getCompanyStockPrice(companyId, from, to);
//	}

//	@PostMapping("/excel-upload")
//	public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
//		return companyClient.uploadExcel(file);
//	}

}