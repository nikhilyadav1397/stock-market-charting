import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/shared/company.model';
import { CompanyService } from 'src/app/shared/company.service';

@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css']
})
export class UpdateCompanyComponent implements OnInit {
  company: Company = {
    companyName: '',
    brief: '',
    sector: '',
    ceo: '',
    stockExchanges: [],
    codeInStockExchanges: []
  };
  name: string;

  constructor(private router: Router, private route: ActivatedRoute, private companyService: CompanyService) {
    
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.company = {
        companyName: params.companyName,
        brief: params.brief,
        sector: params.sector,
        ceo: params.ceo,
        stockExchanges: [],
        codeInStockExchanges: []
      };
      this.name = params.companyName;
    });
  }

  updateCompany(name) {
    this.companyService.updateCompany(this.company, this.name);
    this.router.navigate(['/companies']);
  }

}
