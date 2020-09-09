package com.nikhil.phase3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nikhil.phase3.model.Ipo;

@Repository
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8888" })
public interface IpoRepository extends JpaRepository<Ipo, String> {
	public Iterable<Ipo> findByOpeningDateBetween(String startTime, String endTime);

	public Iterable<Ipo> findByCompanyName(String companyName);

	public Iterable<Ipo> findByCompanyNameAndOpeningDateBetween(String companyName, String startTime, String endTime);

	public boolean existsByCompanyName(String name);
}
