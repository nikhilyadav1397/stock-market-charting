package com.nikhil.phase3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.phase3.dto.StockDto;
import com.nikhil.phase3.service.StockService;

@RestController
@RequestMapping("stocks")
public class StockController {

	public StockController() {
	}

	@Autowired
	private StockService stockService;

	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<StockDto>> findAll() {
		return new ResponseEntity<List<StockDto>>(stockService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/create")
	@Transactional
	public ResponseEntity<StockDto> addStock(@RequestBody StockDto stockDto) {
		return new ResponseEntity<StockDto>(stockService.addStock(stockDto), HttpStatus.OK);
	}
}
