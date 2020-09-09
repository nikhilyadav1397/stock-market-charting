package com.nikhil.phase3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.phase3.dto.SectorDto;
import com.nikhil.phase3.service.SectorService;

@RestController
@RequestMapping("sectors")
public class SectorController {
    private SectorService sectorService;
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<SectorDto>> getAllSectors(){
        return new ResponseEntity<Iterable<SectorDto>>(sectorService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<SectorDto> addSector(@RequestBody SectorDto sectorDto){
    	return new ResponseEntity<SectorDto>(sectorService.addSector(sectorDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{sectorName}")
    @Transactional
    public ResponseEntity<SectorDto> deleteSector(@PathVariable String sectorName){
        SectorDto sectorDto = sectorService.findByName(sectorName).iterator().next();
        sectorService.deleteSectorByName(sectorName);
        return new ResponseEntity<SectorDto>(sectorDto, HttpStatus.OK);
    }

    @PostMapping("/update")
    @Transactional
    public ResponseEntity<SectorDto> updateSector(@RequestBody SectorDto sectorDto){
    	return new ResponseEntity<SectorDto>(sectorService.addSector(sectorDto), HttpStatus.OK);
    }
}
