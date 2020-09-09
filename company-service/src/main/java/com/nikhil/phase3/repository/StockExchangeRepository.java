package com.nikhil.phase3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nikhil.phase3.model.StockExchange;

@Repository
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8888" })
public interface StockExchangeRepository extends JpaRepository<StockExchange, String> {
	public boolean existsByStockExchange(String stockExchange);

	public boolean existsByExchangeId(String id);

	public void deleteByExchangeId(String id);

	public Object findByExchangeId(String id);

	@Query(value = "SELECT stock_exchanges from COMPANY_STOCK_EXCHANGES where company_id = ?1", nativeQuery = true)
	public List<String> findAllByCompanyId(Integer id);
}
