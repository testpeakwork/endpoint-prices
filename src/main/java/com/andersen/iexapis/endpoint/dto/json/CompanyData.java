package com.andersen.iexapis.endpoint.dto.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
@ApiModel(description = "Company with filtered stock data")
public class CompanyData {
    @ApiModelProperty(example = "FB", required = true, value = "Company symbol")
    private String symbol;
    @ApiModelProperty(example = "https://storage.googleapis.com/iex/api/logos/FB.png", required = true, value = "Link to company logo")
    private String logo;
    @ApiModelProperty(example = "Facebook Inc.", required = true, value = "Company name")
    private String name;

    private List<StockData> stocks;
}
