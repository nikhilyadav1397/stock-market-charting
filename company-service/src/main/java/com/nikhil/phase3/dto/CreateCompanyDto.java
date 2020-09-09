package com.nikhil.phase3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCompanyDto {
    private String companyName;
    private String ceo;
    private String sector;
    private String brief;
}

