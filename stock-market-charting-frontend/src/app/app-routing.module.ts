import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompaniesComponent } from './components/companies/companies.component';
import { AddCompanyComponent } from "./components/add-company/add-company.component";
import { UpdateCompanyComponent } from './components/update-company/update-company.component';
import { ShowStocksComponent } from './components/show-stocks/show-stocks.component';
import { StockExchangesComponent } from "./components/stock-exchanges/stock-exchanges.component";
import { AddStockExchangeComponent } from "./components/add-stock-exchange/add-stock-exchange.component";
import { LoginComponent } from "./components/login/login.component";
import { SignupComponent } from "./components/signup/signup.component";

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'companies', component: CompaniesComponent },
  { path: 'addCompany', component: AddCompanyComponent },
  { path: 'updateCompany', component: UpdateCompanyComponent },
  { path: 'stocks', component: ShowStocksComponent },
  { path: 'stockExchanges', component: StockExchangesComponent },
  { path: 'addExchange', component: AddStockExchangeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }