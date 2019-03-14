package com.andersen.iexapis.endpoint.dto.json;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
public class CompanyData {
    private String symbol;
    private String logo;
    private String name;
    private List<StockData> stocks;
}
