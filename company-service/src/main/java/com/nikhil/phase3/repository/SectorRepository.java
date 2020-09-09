package com.nikhil.phase3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nikhil.phase3.model.Sector;

@Repository
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8888"})
public interface SectorRepository extends JpaRepository<Sector,String> {
    public Optional<Sector> findBySectorName(String sectorName);
    public boolean existsBySectorName(String name);
    public Optional<Sector> deleteBySectorName(String name);
}
