import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/shared/company.model';
import { ActivatedRoute } from '@angular/router';
import { Stock } from "../../shared/stock.model";
import { CompanyService } from 'src/app/shared/company.service';

@Component({
  selector: 'app-show-stocks',
  templateUrl: './show-stocks.component.html',
  styleUrls: ['./show-stocks.component.css']
})
export class ShowStocksComponent implements OnInit {
  stocks: Stock[];
  company: Company = {
    companyName: '',
    brief: '',
    sector: '',
    ceo: '',
    stockExchanges: [],
    codeInStockExchanges: []
  };
  exchangeSelected: boolean =  false;
  
  constructor(private route: ActivatedRoute, private companyService: CompanyService) { }

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
    });
    this.getAllStocks();
  }

  getAllStocks(){
    this.companyService.getAllStocks(this.company);
  }
}
