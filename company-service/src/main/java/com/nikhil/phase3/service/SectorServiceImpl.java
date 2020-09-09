package com.nikhil.phase3.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.nikhil.phase3.dto.CompanyDto;
import com.nikhil.phase3.dto.SectorDto;
import com.nikhil.phase3.model.Sector;
import com.nikhil.phase3.repository.SectorRepository;

@Service
public class SectorServiceImpl implements SectorService{

    private SectorRepository sectorRepo;
    private ModelMapper mapper;

    public SectorServiceImpl(SectorRepository sectorRepository, ModelMapper modelMapper) {
        this.sectorRepo = sectorRepository;
        this.mapper = modelMapper;
    }

    @Override
    public Iterable<SectorDto> findAll() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<SectorDto>>(){}.getType();
        List<SectorDto> sectorDtoList = mapper.map(sectorRepo.findAll(),listType);
        return sectorDtoList;
    }

    @Override
    public Iterable<SectorDto> findById(String id) {
        return null;
    }

    @Override
    public Iterable<SectorDto> findByName(String name) {
        return null;
    }

    @Override
    public SectorDto addSector(SectorDto sectorDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Sector sector =sectorRepo.save(mapper.map(sectorDto,Sector.class));
        return mapper.map(sector,SectorDto.class);
    }

    @Override
    public SectorDto deleteSectorByName(String name) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Sector sector =  sectorRepo.deleteBySectorName(name).get();
        return mapper.map(sector,SectorDto.class);
    }

    @Override
    public Iterable<CompanyDto> findCompanyBySector(String sectorName) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
        if(sectorRepo.existsBySectorName(sectorName)) {
            List<CompanyDto> companyDtoList = mapper.map(sectorRepo.findBySectorName(sectorName).get().getCompanyList(), listType);
            return companyDtoList;
        }
        return null;
    }

    @Override
    public boolean ifExistsById(String id){
        return sectorRepo.existsById(id);
    }
}
