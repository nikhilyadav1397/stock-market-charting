package com.nikhil.phase3.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nikhil.phase3.dto.StockDto;
import com.nikhil.phase3.model.Stock;

@Repository
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8888"})
public interface StockRepository extends JpaRepository<Stock,String> {
    List<StockDto> findByCompanyCode(String companyCode);

    @Query("SELECT s FROM Stock s WHERE s.companyCode = ?1 and s.stockExchange = ?2 and (s.dateTime BETWEEN ?3 and ?4)")
    public List<Stock> findStocksFromTo(String companyCode, String exchange, LocalDateTime localDateStart, LocalDateTime localDateEnd);

    @Query("SELECT s FROM Stock s")
    public List<Stock> findStocksForCompany(String companyCode);
}
