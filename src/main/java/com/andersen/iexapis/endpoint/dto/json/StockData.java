package com.andersen.iexapis.endpoint.dto.json;

import lombok.Data;

import java.util.Date;

@Data
public class StockData {
    private Date dateTime;
    private double price;
}
