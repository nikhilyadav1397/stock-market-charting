import { Injectable } from "@angular/core";
import { Observable, BehaviorSubject } from "rxjs";
import { HttpClient } from '@angular/common/http';
import { Company } from "./company.model";
import { StockExchange } from './stock-exchange.model';

@Injectable({
    providedIn: 'root'
})
export class CompanyService {
    exchanges: string[] = [];

    constructor(private http: HttpClient) { }

    getAllCompanies(): Observable<Array<Company>> {
        var list: BehaviorSubject<any> = new BehaviorSubject<any>([]);
        this.http.get("http://localhost:8888/companies/getAll").subscribe(
            (data) => {
                list.next(data);
            }
        );
        return list;
    }

    updateCompany(company, name){
        this.http.put(`http://localhost:8888/companies/update/${name}`, company).subscribe((e) => {
            console.log(e);
        });
    }

    addCompany(company: Company){
        this.http.post("http://localhost:8888/companies/create", company).subscribe((e) => {
            console.log(e);
        });
    }

    deleteCompany(company: Company){
        console.log(company)
        this.http.delete(`http://localhost:8888/companies/delete/${company.companyName}`).subscribe((e) => {
            console.log(e);
        });
    }

    getAllStocks(company: Company){
        console.log(company);
    }

    addExchange(exchange: StockExchange){
        this.http.post("http://localhost:8888/exchanges/create", exchange).subscribe((e) => {
            console.log(e);
        });
    }

    getAllExchanges(): Observable<Array<StockExchange>> {
        var list: BehaviorSubject<any> = new BehaviorSubject<any>([]);
        this.http.get("http://localhost:8888/exchanges/getAll").subscribe(
            (data) => {
                console.log(data);
                list.next(data);
            }
        );
        return list;
    }
}