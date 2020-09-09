package com.nikhil.phase3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nikhil.phase3.model.Stock;

@RestController
@RequestMapping("/excel")
public class ExcelController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/upload")
	public List<Stock> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
		List<Stock> stockList = new ArrayList<Stock>();
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			Stock tempStock = new Stock();
			XSSFRow row = worksheet.getRow(i);
			String companyCode = row.getCell(0).toString();
			tempStock.setCompanyCode(Integer.parseInt(companyCode.substring(0, companyCode.length()-2)));
			tempStock.setStockId(UUID.randomUUID().toString());
			tempStock.setStockExchange(row.getCell(1).getStringCellValue());
			tempStock.setPrice(row.getCell(2).getNumericCellValue());
			String date = row.getCell(3).getDateCellValue().toString().trim();
			tempStock.setDate(date);
			String time = row.getCell(4).getStringCellValue().trim();
			tempStock.setTime(time);
			stockList.add(tempStock);
			logger.info("Stock --> {}", tempStock);
		}
		workbook.close();
		return stockList;
	}
}
