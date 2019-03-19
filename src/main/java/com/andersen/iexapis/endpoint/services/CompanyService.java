package com.andersen.iexapis.endpoint.services;

import com.andersen.iexapis.endpoint.dto.json.CompanyData;

import java.util.Date;
import java.util.List;

/**
 * Service for getting company stock data
 *
 * @author Dzemianchyk_AI
 */
public interface CompanyService {
    /**
     * Get company stock data by symbols throw interval
     *
     * @param symbols list of company symbols for filter
     * @param from    start of interval
     * @param to      end of interval
     * @return list of companies with filtered prices
     */
    List<CompanyData> getCompaniesStocks(List<String> symbols, Date from, Date to);
}
