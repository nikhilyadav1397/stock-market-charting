import { Component, OnInit, Input } from '@angular/core';
import { StockExchange } from "../../shared/stock-exchange.model";
import { Router } from "@angular/router";
import { CompanyService } from "../../shared/company.service";

@Component({
  selector: 'app-show-stock-exchanges',
  templateUrl: './show-stock-exchanges.component.html',
  styleUrls: ['./show-stock-exchanges.component.css']
})
export class ShowStockExchangesComponent implements OnInit {
  @Input() exchange: StockExchange;

  constructor(private companyService: CompanyService, private router: Router) { }

  ngOnInit(): void {
  }

  showCompanies(exchange: StockExchange){

  }

  updateExchange(exchange: StockExchange){

  }

  deleteExchange(){

  }

}
