import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CompaniesComponent } from './components/companies/companies.component';
import { AppRoutingModule } from './app-routing.module';
import { ShowCompaniesComponent } from './components/show-companies/show-companies.component';
import { ShowStocksComponent } from './components/show-stocks/show-stocks.component';
import { AddCompanyComponent } from './components/add-company/add-company.component';
import { UpdateCompanyComponent } from './components/update-company/update-company.component';
import { StockExchangesComponent } from './components/stock-exchanges/stock-exchanges.component';
import { AddStockExchangeComponent } from './components/add-stock-exchange/add-stock-exchange.component';
import { ShowStockExchangesComponent } from './components/show-stock-exchanges/show-stock-exchanges.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CompaniesComponent,
    ShowCompaniesComponent,
    ShowStocksComponent,
    AddCompanyComponent,
    UpdateCompanyComponent,
    StockExchangesComponent,
    AddStockExchangeComponent,
    ShowStockExchangesComponent,
    LoginComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
