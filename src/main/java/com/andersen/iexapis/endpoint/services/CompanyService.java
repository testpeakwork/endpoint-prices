package com.andersen.iexapis.endpoint.services;

import com.andersen.iexapis.endpoint.dao.CompanyDao;
import com.andersen.iexapis.endpoint.dao.StockDao;
import com.andersen.iexapis.endpoint.dto.datastore.Company;
import com.andersen.iexapis.endpoint.dto.datastore.Stock;
import com.andersen.iexapis.endpoint.dto.json.CompanyData;
import com.andersen.iexapis.endpoint.dto.json.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class CompanyService {
    private final CompanyDao companyDao;

    private final StockDao stockDao;

    @Autowired
    public CompanyService(CompanyDao companyDao, StockDao stockDao) {
        this.companyDao = companyDao;
        this.stockDao = stockDao;
    }

    public List<CompanyData> getCompaniesStocks(@Valid List<@Size(min=2, max=10) String> symbols,
                                                @Valid Date from,
                                                @Valid Date to) {
        Iterable<Company> companies = companyDao.findAllById(symbols);
        List<CompanyData> companiesData = new ArrayList<>();
        companies.forEach(company -> companiesData.add(createCompanyData(company, from, to)));
        return companiesData;
    }

    private CompanyData createCompanyData(Company company, Date from, Date to) {
        CompanyData companyData = new CompanyData();
        companyData.setLogo(company.getLogo());
        companyData.setName(company.getName());
        companyData.setSymbol(company.getSymbol());
        List<StockData> stocksData = new ArrayList<>();
        List<Stock> stocks = getStocksByParams(company.getSymbol(), from, to);
        stocks.forEach(stock -> stocksData.add(createStockData(stock)));
        companyData.setStocks(stocksData);
        return companyData;
    }

    private List<Stock> getStocksByParams (String companySymbol, Date from, Date to) {
        List<Stock> stocks;
        if(from == null && to == null) {
            stocks = stockDao.findAllByCompanySymbol(companySymbol);
        } else if(from == null && to != null) {
            stocks = stockDao.findAllByCompanySymbolAndDateTimeLessThan(companySymbol, to);
        } else if(from != null && to == null) {
            stocks = stockDao.findAllByCompanySymbolAndDateTimeGreaterThan(companySymbol, from);
        } else {
            stocks = stockDao.findAllByCompanySymbolAndDateTimeGreaterThanAndDateTimeLessThan(companySymbol, from, to);
        }
        return stocks;
    }

    private StockData createStockData(Stock stock) {
        StockData stockData = new StockData();
        stockData.setDateTime(stock.getDateTime().toDate());
        stockData.setPrice(stock.getPrice());
        return stockData;
    }
}
