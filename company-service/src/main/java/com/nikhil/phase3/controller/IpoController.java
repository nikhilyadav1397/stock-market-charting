package com.nikhil.phase3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.phase3.model.Ipo;
import com.nikhil.phase3.service.IpoService;

@RestController
@RequestMapping("ipos")
public class IpoController {
    private IpoService ipoService;

    public IpoController(IpoService ipoService) {
        this.ipoService = ipoService;
    }

    @GetMapping("/getByCompany/{companyName}")
    public ResponseEntity<Iterable<Ipo>> getIpos(@PathVariable String companyName) {
        if (ipoService.ifExistsByName(companyName)) {
            return new ResponseEntity<Iterable<Ipo>>(ipoService.findByCompanyName(companyName), HttpStatus.OK);
        }
        return new ResponseEntity<Iterable<Ipo>>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<Ipo> updateIpo(@RequestBody Ipo ipo) {
        if(ipoService.ifExistsById(ipo.getIpoId())) {
            return new ResponseEntity<Ipo>(ipoService.addIpo(ipo), HttpStatus.CREATED);
        }
        return new ResponseEntity<Ipo>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Ipo> addIpo(@RequestBody Ipo ipo) {
        return new ResponseEntity<Ipo>(ipoService.addIpo(ipo), HttpStatus.CREATED);
    }

}
