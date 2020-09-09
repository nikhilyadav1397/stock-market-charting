export class Company {
    companyName: string;
    ceo: string;
    stockExchanges: string[];
    sector: string;
    brief: string;
    codeInStockExchanges: string[];

    constructor(companyName: string, ceo: string, stockExchanges: string[], sector: string, brief: string, codeInStockExchanges: string[]){
        this.companyName = companyName;
        this.ceo = ceo;
        this.stockExchanges = stockExchanges;
        this.sector = sector;
        this.codeInStockExchanges = codeInStockExchanges;
        
    }
}