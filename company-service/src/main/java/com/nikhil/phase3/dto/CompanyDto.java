package com.nikhil.phase3.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyDto {
	private String companyId;
    private String companyName;
    private String ceo;
    private List<String> stockExchanges = new ArrayList<String>();
    private String sector;
    private String brief;
    private List<String> companyCodes = new ArrayList<String>();
}

