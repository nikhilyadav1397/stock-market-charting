package com.nikhil.phase3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String companyId;
    private String companyName;
    private String brief;
    private String sector;
    private String ceo;
    @ElementCollection
    private List<String> stockExchanges = new ArrayList<>();
    @ElementCollection
    private List<String> companyCodes = new ArrayList<>();
}
