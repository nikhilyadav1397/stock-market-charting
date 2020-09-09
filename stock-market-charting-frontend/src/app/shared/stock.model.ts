export class Stock {
    stockId: string;
    companyCode: string;
    stockExchange: string;
    pricePerShare: number;
    datetime: string;

    constructor(stockId: string, companyCode: string, stockExchange: string, pricePerShare: number, datetime: string){
        this.stockId = stockId;
        this.companyCode = companyCode;
        this.datetime = datetime;
        this.pricePerShare = pricePerShare;
        this.stockExchange = stockExchange;
    }
}