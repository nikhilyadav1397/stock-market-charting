import { Component, OnInit, OnChanges } from '@angular/core';
import { CompanyService } from "../../shared/company.service";
import { Company } from 'src/app/shared/company.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit, OnChanges {
  companies: Company[];
  showAllCompanies: boolean;
  company: Company;

  constructor(private companyService: CompanyService, private router: Router) { }

  ngOnInit(): void {
    this.getAll();
  }

  ngOnChanges(){
    this.getAll();
  }

  ngAfterContentInit(){
    this.getAll();
  }

  getAll() {
    this.showAllCompanies = true;
    this.companyService.getAllCompanies().subscribe(data => {
      this.companies = data;
    })
  }

  onAdd() {
    this.router.navigate(['/addCompany']);
  }
}
