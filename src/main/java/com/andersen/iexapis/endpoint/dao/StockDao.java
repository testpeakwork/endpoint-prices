package com.andersen.iexapis.endpoint.dao;

import com.andersen.iexapis.endpoint.dto.datastore.Company;
import com.andersen.iexapis.endpoint.dto.datastore.Stock;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface StockDao extends DatastoreRepository<Stock, String> {
    List<Stock> findAllByCompanySymbol(String symbol);
    List<Stock> findAllByCompanySymbolAndDateTimeGreaterThan(String symbol, Date from);
    List<Stock> findAllByCompanySymbolAndDateTimeLessThan(String symbol, Date to);
    List<Stock> findAllByCompanySymbolAndDateTimeGreaterThanAndDateTimeLessThan(String symbol, Date from, Date to);
}
