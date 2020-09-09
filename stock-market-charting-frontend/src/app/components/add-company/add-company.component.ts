import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/shared/company.model';
import { Router } from '@angular/router';
import { CompanyService } from 'src/app/shared/company.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit {
  company: Company = {
    companyName: '',
    brief: '',
    sector: '',
    ceo: '',
    stockExchanges: [],
    codeInStockExchanges: []
  };

  constructor(private router: Router, private companyService: CompanyService) { }

  ngOnInit(): void {
  }

  addCompany(){
    this.companyService.addCompany(this.company);
    this.router.navigate(['/companies']);
  }

}
