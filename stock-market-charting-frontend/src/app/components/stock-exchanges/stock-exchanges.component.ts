import { Component, OnInit } from '@angular/core';
import { StockExchange } from "../../shared/stock-exchange.model";
import { CompanyService } from 'src/app/shared/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-stock-exchanges',
  templateUrl: './stock-exchanges.component.html',
  styleUrls: ['./stock-exchanges.component.css']
})
export class StockExchangesComponent implements OnInit {
  exchanges: StockExchange[];
  showAllExchanges: boolean = false;

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

  onAdd(){
    this.router.navigate(['/addExchange']);
  }

  getAll() {
    this.showAllExchanges = true;
    this.companyService.getAllExchanges().subscribe(data => {
      this.exchanges = data;
    })
  }
}
