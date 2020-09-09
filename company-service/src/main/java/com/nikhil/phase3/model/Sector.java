package com.nikhil.phase3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sector {

	@Id
	private String id;
	private String sectorName;
	private String brief;
	@ElementCollection
	private List<Company> companyList = new ArrayList<Company>();

	public Sector(String id, String sectorName, String brief) {
		this.id = id;
		this.sectorName = sectorName;
		this.brief = brief;
	}
}
