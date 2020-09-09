package com.nikhil.phase3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nikhil.phase3.model.Company;

@Repository
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8888"})
public interface CompanyRepository extends JpaRepository<Company, String> {
	public Optional<Company> findByCompanyId(String companyId);

	public List<Company> findByCompanyNameContaining(String pattern);

	public Optional<Company> findByCompanyName(String companyName);

	public List<Company> findBySector(String sector);

	public boolean existsByCompanyName(String companyName);

	@Query("SELECT c FROM Company c")
	public List<Company> findByStockExchange(String stockExchange);
}
