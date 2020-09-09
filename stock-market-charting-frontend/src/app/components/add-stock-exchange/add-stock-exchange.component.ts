import { Component, OnInit } from '@angular/core';
import { StockExchange } from 'src/app/shared/stock-exchange.model';
import { Router } from '@angular/router';
import { CompanyService } from 'src/app/shared/company.service';

@Component({
  selector: 'app-add-stock-exchange',
  templateUrl: './add-stock-exchange.component.html',
  styleUrls: ['./add-stock-exchange.component.css']
})
export class AddStockExchangeComponent implements OnInit {
  exchange: StockExchange = {
    exchangeId: '',
    stockExchange: '',
    brief: '',
    contactAddress: '',
    remarks: ''
}

  constructor(private router: Router, private companyService: CompanyService) { }

  ngOnInit(): void {
  }

  addExchange(){
    this.companyService.addExchange(this.exchange);
    this.router.navigate(['/stockExchanges']);
  }

}
