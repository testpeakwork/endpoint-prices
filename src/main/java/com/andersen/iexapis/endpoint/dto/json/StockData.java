package com.andersen.iexapis.endpoint.dto.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Stock data at a certain time")
public class StockData {
    @ApiModelProperty(required = true, value = "Datetime when price was actual")
    private Date dateTime;
    @ApiModelProperty(required = true, value = "Price at a certain time", example = "71.12")
    private double price;
}
