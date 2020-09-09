package com.nikhil.phase3.service;

import org.springframework.stereotype.Service;

import com.nikhil.phase3.model.Ipo;
import com.nikhil.phase3.repository.IpoRepository;

@Service
public class IpoServiceImpl implements IpoService{
    private IpoRepository ipoRepo;


    public IpoServiceImpl(IpoRepository ipoRepository){
        this.ipoRepo = ipoRepository;
    }

    @Override
    public Iterable<Ipo> getIpo() {
        return ipoRepo.findAll();
    }

    @Override
    public Ipo addIpo(Ipo ipo) {
        return ipoRepo.save(ipo);

    }

    @Override
    public Iterable<Ipo> findByCompanyName(String companyName) {
        return ipoRepo.findByCompanyName(companyName);
    }

    @Override
    public boolean ifExistsById(String id){
        return ipoRepo.existsById(id);
    }

    public boolean ifExistsByName(String name){return ipoRepo.existsByCompanyName(name);}
}
