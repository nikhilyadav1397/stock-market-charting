import { Component, OnInit, Input } from '@angular/core';
import { CompanyService } from "../../shared/company.service";
import { Company } from "../../shared/company.model";
import { Router, NavigationExtras } from '@angular/router';

@Component({
  selector: 'app-show-companies',
  templateUrl: './show-companies.component.html',
  styleUrls: ['./show-companies.component.css']
})
export class ShowCompaniesComponent implements OnInit {
  @Input() company: Company;
  showIpos: boolean = false;
  showStocks: boolean = false;

  constructor(private companyService: CompanyService, private router: Router) { }

  ngOnInit(): void {
  }

  showStock(company: Company) {
    let navigationExtras: NavigationExtras = {
      queryParams: {
        companyName: company.companyName,
        brief: company.brief,
        sector: company.sector,
        ceo: company.ceo,
        stockExchanges: [],
        codeInStockExchanges: []
      }
    };
    this.router.navigate(['/stocks'], navigationExtras);
  }

  showIpo() {

  }

  updateCompany(company: Company) {
    let navigationExtras: NavigationExtras = {
      queryParams: {
        companyName: company.companyName,
        brief: company.brief,
        sector: company.sector,
        ceo: company.ceo,
        stockExchanges: [],
        codeInStockExchanges: []
      }
    };
    this.router.navigate(['/updateCompany'], navigationExtras);
  }

  deleteCompany(){
    this.companyService.deleteCompany(this.company);
  }

}
